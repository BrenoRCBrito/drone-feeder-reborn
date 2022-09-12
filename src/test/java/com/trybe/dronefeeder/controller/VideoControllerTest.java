// package com.trybe.dronefeeder.controller;

// import static org.hamcrest.Matchers.containsString;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.trybe.dronefeeder.service.VideoService;
// import java.io.File;
// import java.nio.file.Files;

// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockMultipartFile;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;

// @RunWith(SpringRunner.class)
// @WebMvcTest(VideoController.class)
// public class VideoControllerTest {

//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean

//   private VideoService service;

//   @Test
//   public void succesfulFindAll() throws Exception {
//     mockMvc.perform(get("/video"))
//         .andExpect(status().isOk())
//         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//         .andExpect(content().string(containsString("[]")));
//   }

//   @Test
//   public void succesfulUpload() throws Exception {
//     File file = new File(
//         System.getProperty("user.dir")
//             + "/src/test/java/com/trybe/dronefeeder/mocks/testVideo1.mp4");
//     byte[] fileContent = Files.readAllBytes(file.toPath());
//     MockMultipartFile files = new MockMultipartFile("files",
//         "videoplayback.mp4", "multipart/form-data",
//         fileContent);
//     mockMvc.perform(multipart("/video/upload").file(files))
//         .andExpect(status().isOk());
//   }

//   @Test
//   public void succesfulDownload() throws Exception {
//     mockMvc.perform(get("/video/download/arquivo.mp4"))
//         .andExpect(status().isOk());
//   }

// }
