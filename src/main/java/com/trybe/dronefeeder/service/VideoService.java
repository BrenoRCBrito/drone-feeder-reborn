package com.trybe.dronefeeder.service;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import com.trybe.dronefeeder.domain.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

  private static final String DIRECTORY = directory();

  /**
   * Method that sets the video upload and downlaod directory.
   * 
   * @return a directory String.
   */
  public static String directory() {
    String env = System.getenv("DIRECTORY");
    if (env == null) {
      String defaultDirectory = StringUtils.cleanPath(System.getProperty("user.dir")
          + "/src/main/resources/videos");
      new File(defaultDirectory).mkdir();
      return defaultDirectory;
    }
    return env;
  }

  /**
   * Method that query all the available videos.
   * 
   * @return a list of Videos.
   */
  public List<Video> findAll() {
    File file = new File(DIRECTORY);
    String[] pathNames = file.list();
    List<Video> videos = new ArrayList<>();
    for (int i = 0; i < pathNames.length; i += 1) {
      Video video = new Video(pathNames[i]);
      video.setDownloadUrl(pathNames[i]);
      videos.add(video);
    }
    return videos;
  }

  /**
   * Method that manages the video upload feature.
   * 
   * @param multipartFiles an upload MultipartFile object.
   * @return a list of Videos.
   * @throws IOException in case of access errors (if the temporary store fails).
   */
  public List<Video> upload(
      List<MultipartFile> multipartFiles) throws IOException {
    List<Video> videos = new ArrayList<>();
    for (MultipartFile file : multipartFiles) {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath();
      copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
      Video video = new Video(fileName);
      video.setDownloadUrl(fileName);
      videos.add(video);
    }
    return videos;
  }

  /**
   * Method that manages the video downlaoad feature.
   * 
   * @param fileName the downloaded file name String.
   * @return a Map with the http required parameters.
   * @throws IOException if the download file doesn't exist.
   */
  public Map<String, Object> download(
      @PathVariable("filename") String fileName) throws IOException {
    Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);
    if (!Files.exists(filePath)) {
      throw new FileNotFoundException(fileName + " was not found on the server");
    }
    Resource resource = new UrlResource(filePath.toUri());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("File-Name", fileName);
    httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-name=" + resource.getFilename());
    Map<String, Object> downloadMap = new HashMap<>();
    downloadMap.put("contentType", MediaType.parseMediaType(Files.probeContentType(filePath)));
    downloadMap.put("httpHeader", httpHeaders);
    downloadMap.put("resource", resource);
    return downloadMap;
  }

}
