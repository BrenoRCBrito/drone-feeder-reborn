// package com.trybe.dronefeeder.service;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.mock.web.MockMultipartFile;
// import org.springframework.web.multipart.MultipartFile;

// import com.trybe.dronefeeder.domain.Video;


// @SpringBootTest()
// public class VideoServiceTest {

//   @Autowired
//   private VideoService service;

//   @Test
//   public void succesfulUpload() throws IOException {
//     File file = new File(
//         System.getProperty("user.dir")
//             + "/src/test/java/com/trybe/dronefeeder/mocks/testVideo1.mp4");
//     byte[] fileContent = Files.readAllBytes(file.toPath());
//     MockMultipartFile multipartFile = new MockMultipartFile("files",
//         "videoplayback.mp4", "multipart/form-data",
//         fileContent);
//     List<MultipartFile> multipartFiles = new ArrayList<>();
//     multipartFiles.add(multipartFile);
//     List<Video> b = service.upload(multipartFiles);
//     Assertions.assertEquals(1, b.size());
//   }
// }
