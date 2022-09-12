// package com.trybe.dronefeeder.controller;

// import static org.hamcrest.Matchers.containsString;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.trybe.dronefeeder.dto.DroneDto;
// import com.trybe.dronefeeder.service.DroneService;

// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;

// @RunWith(SpringRunner.class)
// @WebMvcTest(DroneController.class)
// @AutoConfigureMockMvc
// public class DroneControllerTest {
//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean
//   private DroneService droneService;

//   @Test
//   public void getAllRoute() throws Exception {
//     mockMvc.perform(get("/drone"))
//         .andExpect(status().isOk())
//         .andExpect(content().string(containsString("[]")));
//   }

//   @Test
//   public void findByIdRoute() throws Exception {
//     mockMvc.perform(get("/drone/1"))
//         .andExpect(status().isOk());
//   }

//   @Test
//   public void createRoute() throws Exception {
//     final var drone = new DroneDto("90.0", "80.0", "2022-07-20");

//     mockMvc.perform(post("/drone")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(new ObjectMapper().writeValueAsString(drone)))
//         .andExpect(status().isCreated());
//   }

//   @Test
//   public void updateRoute() throws Exception {
//     final var drone = new DroneDto("90.0", "80.0", "2022-07-20");

//     mockMvc.perform(put("/drone/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(new ObjectMapper().writeValueAsString(drone)))
//         .andExpect(status().isOk());
//   }

//   @Test
//   public void deleteRoute() throws Exception {
//     mockMvc.perform(delete("/drone/1")
//         .contentType(MediaType.APPLICATION_JSON))
//         .andExpect(status().isOk());
//   }
// }
