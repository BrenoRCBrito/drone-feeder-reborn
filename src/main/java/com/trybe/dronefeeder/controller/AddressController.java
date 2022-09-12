package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.contract.requests.AddressRequest;
import com.trybe.dronefeeder.contract.response.AddressResponse;
import com.trybe.dronefeeder.service.AddressService;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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
@RequestMapping("/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  /**
   * Create Address Controller.
   * 
   * @param address AddressRequest Object.
   * @return ResponseEntity Object with status 201 and a body with the created
   *         Address.
   */
  @PostMapping()
  public ResponseEntity<AddressResponse> create(@Valid @RequestBody AddressRequest address) {
    AddressResponse addressCreated = addressService.create(address);
    return ResponseEntity.status(HttpStatus.CREATED).body(addressCreated);
  }

  /**
   * FindAll Address Controller.
   * 
   * @return ResponseEntity Object with status 200 and a body with all deliveries.
   */
  @GetMapping()
  public ResponseEntity<List<AddressResponse>> findAll() {
    List<AddressResponse> address = addressService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(address);
  }

  /**
   * FindById Address Controller.
   * 
   * @param id UUID Object with the requested Address id.
   * @return ResponseEntity Object with status 200 and a body with the requested
   *         Address.
   */
  @GetMapping("/{id}")
  public ResponseEntity<AddressResponse> findById(@PathVariable("id") UUID id) {
    AddressResponse address = addressService.findById(id);
    return ResponseEntity.ok(address);
  }

  /**
   * Update Address Controller.
   * 
   * @param address AddressRequest Object with all the updates.
   * @param id      UUID Object with the requested Address id.
   * @return ResponseEntity Object with status 200 and a body with the updated
   *         Address.
   */
  @PutMapping("/{id}")
  public ResponseEntity<AddressResponse> update(
      @RequestBody AddressRequest address,
      @PathVariable("id") UUID id) {
    AddressResponse edited = addressService.update(address, id);
    return ResponseEntity.status(HttpStatus.OK).body(edited);
  }

  /**
   * Delete Address Controller.
   * 
   * @param id UUID Object with the requested Address id.
   * @return ResponseEntity Object with status 200 and a body with the deleted
   *         Address.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<AddressResponse> delete(@PathVariable("id") UUID id) {
    AddressResponse removed = addressService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(removed);
  }

}
