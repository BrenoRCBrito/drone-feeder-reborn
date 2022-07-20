package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.dto.DeliveryDto;
import com.trybe.dronefeeder.service.DeliveryService;

import java.util.HashMap;
import java.util.List;

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

  /** create controller. */
  @PostMapping()
  public ResponseEntity<DeliveryDto> create(@RequestBody DeliveryDto delivery) {
    DeliveryDto deliveryCreated = deliveryService.create(delivery);
    return ResponseEntity.status(HttpStatus.CREATED).body(deliveryCreated);
  }
  
  /** getAll controller. */
  @GetMapping()
  public ResponseEntity<List<DeliveryDto>> findAll() {
    List<DeliveryDto> delivery = deliveryService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(delivery);
  }

  /** update controller. */
  @PutMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> edit(
      @RequestBody DeliveryDto delivery,
      @PathVariable("id") Long id) {
    DeliveryDto edited = deliveryService.update(delivery, id);
    System.out.println(edited.getId());

    HashMap<String, String> obj = new HashMap<String, String>();
    String message = String.format("ID [%d] atualizado", edited.getId());
    obj.put("message", message);
    return ResponseEntity.status(HttpStatus.OK).body(obj);
  }

  /** delete controller. */
  @DeleteMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
    DeliveryDto removed = deliveryService.delete(id);
    HashMap<String, String> obj = new HashMap<String, String>();

    String message = String.format("ID [%d] removido", removed.getId());
    obj.put("message", message);

    return ResponseEntity.status(HttpStatus.OK).body(obj);
  }
} 