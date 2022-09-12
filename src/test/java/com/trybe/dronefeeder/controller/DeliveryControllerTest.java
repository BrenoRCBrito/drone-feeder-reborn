// package com.trybe.dronefeeder.controller;

// import static org.hamcrest.Matchers.containsString;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.trybe.dronefeeder.dto.DeliveryDto;
// import com.trybe.dronefeeder.dto.DroneDto;
// import com.trybe.dronefeeder.service.DeliveryService;
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
// @WebMvcTest(DeliveryController.class)
// @AutoConfigureMockMvc
// public class DeliveryControllerTest {
//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean
//   private DeliveryService deliveryService;

//   @Test
//   public void getAllRoute() throws Exception {
//     mockMvc.perform(get("/delivery"))
//         .andExpect(status().isOk())
//         .andExpect(content().string(containsString("[]")));
//   }

//   // @Test
//   // public void createRoute() throws Exception {
//   //   final var drone = new DroneDto("90.0",  "180.0", "2022-07-15");
//   //   final var delivery = new DeliveryDto("Controle", "2022-07-20", drone);

//   //   mockMvc.perform(post("/delivery")
//   //       .contentType(MediaType.APPLICATION_JSON)
//   //       .content(new ObjectMapper().writeValueAsString(delivery)))
//   //       .andExpect(status().isCreated());
//   // }

//   // @Test
//   // public void updateRoute() throws Exception {
//   //   final var drone = new DroneDto("90.0",  "180.0", "2022-07-15");
//   //   final var delivery = new DeliveryDto("Bola", "2022-07-21", drone);

//   //   mockMvc.perform(put("/delivery/1")
//   //       .contentType(MediaType.APPLICATION_JSON)
//   //       .content(new ObjectMapper().writeValueAsString(delivery)))
//   //       .andExpect(status().isOk());
//   // }

//   // @Test
//   // public void deleteRoute() throws Exception {
//   //   mockMvc.perform(delete("/delivery/1")
//   //       .contentType(MediaType.APPLICATION_JSON))
//   //       .andExpect(status().isOk());
//   // }
// }
