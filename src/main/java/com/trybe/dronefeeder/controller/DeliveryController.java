package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.contract.requests.DeliveryRequest;
import com.trybe.dronefeeder.contract.response.DeliveryResponse;
import com.trybe.dronefeeder.service.DeliveryService;

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
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;

  /**
   * Create Delivery Controller.
   * 
   * @param delivery DeliveryRequest Object.
   * @return ResponseEntity Object with status 201 and a body with the created
   *         delivery.
   */
  @PostMapping()
  public ResponseEntity<DeliveryResponse> create(@RequestBody DeliveryRequest delivery) {
    DeliveryResponse deliveryCreated = deliveryService.create(delivery);
    return ResponseEntity.status(HttpStatus.CREATED).body(deliveryCreated);
  }

  /**
   * FindAll Delivery Controller.
   * 
   * @return ResponseEntity Object with status 200 and a body with all deliveries.
   */
  @GetMapping()
  public ResponseEntity<List<DeliveryResponse>> findAll() {
    List<DeliveryResponse> delivery = deliveryService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(delivery);
  }

  /**
   * FindById Delivery Controller.
   * 
   * @param id Long Object with the requested delivery id.
   * @return ResponseEntity Object with status 200 and a body with the requested
   *         delivery.
   */
  @GetMapping("/{id}")
  public ResponseEntity<DeliveryResponse> findById(@PathVariable("id") UUID id) {
    DeliveryResponse delivery = deliveryService.findById(id);
    return ResponseEntity.ok(delivery);
  }

  /**
   * Update Delivery Controller.
   * 
   * @param delivery DeliveryRequest Object with all the updates.
   * @param id       Long Object with the requested delivery id.
   * @return ResponseEntity Object with status 200 and a body with the updated
   *         delivery.
   */
  @PutMapping("/{id}")
  public ResponseEntity<DeliveryResponse> update(
      @RequestBody DeliveryRequest delivery,
      @PathVariable("id") UUID id) {
    DeliveryResponse edited = deliveryService.update(delivery, id);
    return ResponseEntity.status(HttpStatus.OK).body(edited);
  }

  /**
   * Delete Delivery Controller.
   * 
   * @param id Long Object with the requested delivery id.
   * @return ResponseEntity Object with status 200 and a body with the deleted
   *         delivery.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<DeliveryResponse> delete(@PathVariable("id") UUID id) {
    DeliveryResponse removed = deliveryService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(removed);
  }
}
