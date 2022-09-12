package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.contract.requests.PackageRequest;
import com.trybe.dronefeeder.contract.response.PackageResponse;
import com.trybe.dronefeeder.service.PackageService;
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
@RequestMapping("/package")
public class PackageController {

  @Autowired
  private PackageService packageService;

  /**
   * Create Package Controller.
   * 
   * @param pkg PackageRequest Object.
   * @return ResponseEntity Object with status 201 and a body with the created
   *         Package.
   */
  @PostMapping()
  public ResponseEntity<PackageResponse> create(@RequestBody PackageRequest pkg) {
    PackageResponse packageCreated = packageService.create(pkg);
    return ResponseEntity.status(HttpStatus.CREATED).body(packageCreated);
  }

  /**
   * FindAll Package Controller.
   * 
   * @return ResponseEntity Object with status 200 and a body with all deliveries.
   */
  @GetMapping()
  public ResponseEntity<List<PackageResponse>> findAll() {
    List<PackageResponse> pkg = packageService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(pkg);
  }

  /**
   * FindById Package Controller.
   * 
   * @param id UUID Object with the requested Package id.
   * @return ResponseEntity Object with status 200 and a body with the requested
   *         Package.
   */
  @GetMapping("/{id}")
  public ResponseEntity<PackageResponse> findById(@PathVariable("id") UUID id) {
    PackageResponse pkg = packageService.findById(id);
    return ResponseEntity.ok(pkg);
  }

  /**
   * Update Package Controller.
   * 
   * @param pkg PackageRequest Object with all the updates.
   * @param id  UUID Object with the requested Package id.
   * @return ResponseEntity Object with status 200 and a body with the updated
   *         Package.
   */
  @PutMapping("/{id}")
  public ResponseEntity<PackageResponse> update(
      @RequestBody PackageRequest pkg,
      @PathVariable("id") UUID id) {
    PackageResponse edited = packageService.update(pkg, id);
    return ResponseEntity.status(HttpStatus.OK).body(edited);
  }

  /**
   * Delete Package Controller.
   * 
   * @param id UUID Object with the requested Package id.
   * @return ResponseEntity Object with status 200 and a body with the deleted
   *         Package.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<PackageResponse> delete(@PathVariable("id") UUID id) {
    PackageResponse removed = packageService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(removed);
  }
}
