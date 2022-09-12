// package com.trybe.dronefeeder.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;

// import com.trybe.dronefeeder.dto.DroneDto;
// import com.trybe.dronefeeder.exceptions.BadRequestException;
// import com.trybe.dronefeeder.exceptions.ResourceNotFoundException;
// import com.trybe.dronefeeder.model.DroneModel;
// import com.trybe.dronefeeder.repository.DroneRepository;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// public class DroneServiceTest {

//   @InjectMocks
//   private DroneService service ;

//   @Mock
//   private DroneRepository repository;

//   @BeforeEach
//   public void init() {
//     MockitoAnnotations.openMocks(this);
//   }

//   DroneDto droneDto1 = new DroneDto("90.0", "180.0", "2022-07-18");
//   DroneDto wrongLatitudeDroneDto = new DroneDto("1000", "0.0", "2022-07-17");
//   DroneDto wrongLongitudeDroneDto = new DroneDto("0.0", "1000", "2022-07-17");
//   DroneDto wrongDateDroneDto = new DroneDto("90.0", "180.0", "07-2022-18");
//   DroneDto pastDateDroneDto = new DroneDto("90.0", "180.0", "2010-07-18");
//   DroneModel drone1 = new DroneModel((long) 1, "90.0", "180.0", "2022-07-18");
//   DroneModel drone2 = new DroneModel((long) 2, "0.0", "0.0", "2022-07-17");

//   @Test
//   public void findAll() {

//     when(repository.findAll()).thenReturn(Arrays.asList(drone1, drone2));
//     List<DroneModel> drones = service.findAll();
//     Assertions.assertEquals(2, drones.size(), "findAll should return 2 drones");
//     Assertions.assertEquals(List.of(drone1, drone2),
//         drones);
//   }

//   @Test
//   public void succesfulCreate() {
//     when(repository.save(any(DroneModel.class))).thenReturn(drone1);
//     DroneModel drone = service.create(droneDto1);
//     Assertions.assertEquals(drone1, drone);
//   }

//   @Test
//   public void wrongLatitudeCreate() {
//     Exception badRequest = Assertions.assertThrows(BadRequestException.class,
//         () -> service.create(wrongLatitudeDroneDto));
//     assertEquals("The request latitude is wrong", badRequest.getMessage());
//   }

//   @Test
//   public void wrongLongituteCreate() {
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.create(wrongLongitudeDroneDto));
//     assertEquals("The request longitude is wrong", badRequest.getMessage());
//   }

//   @Test
//   public void wrongDateCreate() {
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.create(wrongDateDroneDto));
//     assertEquals("The request date is not valid", badRequest.getMessage());
//   }

//   @Test
//   public void pastDateCreate() {
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.create(pastDateDroneDto));
//     assertEquals("The request date is not valid", badRequest.getMessage());
//   }

//   @Test
//   public void succesfulFindbyId() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     DroneModel drone = service.findById((long) 1);
//     Assertions.assertEquals(drone1, drone, "edit should return the edited drone");
//   }

//   @Test
//   public void failedFindbyId() {
//     Exception notFound = Assertions.assertThrows(ResourceNotFoundException.class,
//         () -> service.findById((long) 1));
//     assertEquals("No id was found", notFound.getMessage());
//   }

//   @Test
//   public void succesfulUpdate() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     when(repository.save(any(DroneModel.class))).thenReturn(drone1);
//     DroneModel drone = service.update(droneDto1, (long) 1);
//     Assertions.assertEquals(drone1, drone);
//   }

//   @Test
//   public void noEntityUpdate() {
//     Exception notFound = Assertions.assertThrows(ResourceNotFoundException.class,
//         () -> service.update(droneDto1, (long) 1));
//     assertEquals("Not possible to edit, the provided id does not exist", notFound.getMessage());
//   }

//   @Test
//   public void wrongLatitudeUpdate() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     Exception badRequest = Assertions.assertThrows(BadRequestException.class,
//         () -> service.update(wrongLatitudeDroneDto, (long) 1));
//     assertEquals("The request latitude is wrong", badRequest.getMessage());
//   }

//   @Test
//   public void wrongLongituteUpdate() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.update(wrongLongitudeDroneDto, (long) 1));
//     assertEquals("The request longitude is wrong", badRequest.getMessage());
//   }

//   @Test
//   public void wrongDateUpdate() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.update(wrongDateDroneDto, (long) 1));
//     assertEquals("The request date is not valid", badRequest.getMessage());
//   }

//   @Test
//   public void pastDateUpdate() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     Exception badRequest = Assertions.assertThrows(
//         BadRequestException.class,
//         () -> service.update(pastDateDroneDto, (long) 1));
//     assertEquals("The request date is not valid", badRequest.getMessage());
//   }

//   @Test
//   public void succesfulDelete() {
//     when(repository.findById((long) 1)).thenReturn(Optional.of(drone1));
//     DroneModel drone = service.delete((long) 1);
//     Assertions.assertEquals(drone1, drone);
//   }

//   @Test
//   public void failedDelete() {
//     Exception notFound = Assertions.assertThrows(ResourceNotFoundException.class,
//         () -> service.delete((long) 1));
//     assertEquals("Not possible to delete, the provided id does not exist", notFound.getMessage());
//   }

// }
