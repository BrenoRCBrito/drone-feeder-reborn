package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.contract.requests.DroneRequest;
import com.trybe.dronefeeder.contract.response.DroneResponse;
import com.trybe.dronefeeder.service.DroneService;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/drone")
public class DroneController {
  @Autowired
  private DroneService droneService;

  /**
   * Create Drone Controller.
   * 
   * @param drone DroneRequest Object.
   * @return ResponseEntity Object with status 201 and a body with the created
   *         drone.
   */
  @PostMapping()
  public ResponseEntity<DroneResponse> create(@RequestBody DroneRequest drone) {
    DroneResponse createdEntity = droneService.create(drone);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
  }

  /**
   * FindAll Drone Controller.
   * 
   * @return ResponseEntity Object with status 200 and a body with all drones.
   */
  @GetMapping()
  public ResponseEntity<List<DroneResponse>> findAll() {
    List<DroneResponse> drones = droneService.findAll();
    return ResponseEntity.ok(drones);
  }

  /**
   * FindById Delivery Controller.
   * 
   * @param id Long Object with the requested drone id.
   * @return ResponseEntity Object with status 200 and a body with the requested
   *         drone.
   */
  @GetMapping("/{id}")
  public ResponseEntity<DroneResponse> findById(@PathVariable("id") UUID id) {
    DroneResponse drone = droneService.findById(id);
    return ResponseEntity.ok(drone);
  }

  /**
   * Update Drone Controller.
   * 
   * @param drone DroneRequest Object with all the updates.
   * @param id    Long Object with the requested drone id.
   * @return ResponseEntity Object with status 200 and a body with the updated
   *         drone.
   */
  @PutMapping("/{id}")
  public ResponseEntity<DroneResponse> update(@RequestBody DroneRequest drone,
      @PathVariable("id") UUID id) {
    DroneResponse updatedEntity = droneService.update(drone, id);
    return ResponseEntity.ok(updatedEntity);
  }

  /**
   * Delete Drone Controller.
   * 
   * @param id Long Object with the requested drone id.
   * @return ResponseEntity Object with status 200 and a body with the deleted
   *         drone.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<DroneResponse> delete(@PathVariable("id") UUID id) {
    DroneResponse deletedEntity = droneService.delete(id);
    return ResponseEntity.ok(deletedEntity);
  }
}