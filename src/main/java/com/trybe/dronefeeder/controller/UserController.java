package com.trybe.dronefeeder.controller;

import com.trybe.dronefeeder.contract.requests.UserRequest;
import com.trybe.dronefeeder.contract.response.UserResponse;
import com.trybe.dronefeeder.service.UserService;
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
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Create User Controller.
   * 
   * @param user UserRequest Object.
   * @return ResponseEntity Object with status 201 and a body with the created
   *         User.
   */
  @PostMapping()
  public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
    UserResponse userCreated = userService.create(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

  /**
   * FindAll User Controller.
   * 
   * @return ResponseEntity Object with status 200 and a body with all deliveries.
   */
  @GetMapping()
  public ResponseEntity<List<UserResponse>> findAll() {
    List<UserResponse> user = userService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  /**
   * FindById User Controller.
   * 
   * @param id String Object with the requested User id.
   * @return ResponseEntity Object with status 200 and a body with the requested
   *         User.
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable("id") String id) {
    UserResponse user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  /**
   * Update User Controller.
   * 
   * @param user UserRequest Object with all the updates.
   * @param id   String Object with the requested User id.
   * @return ResponseEntity Object with status 200 and a body with the updated
   *         User.
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> update(
      @RequestBody UserRequest user,
      @PathVariable("id") String id) {
    UserResponse edited = userService.update(user, id);
    return ResponseEntity.status(HttpStatus.OK).body(edited);
  }

  /**
   * Delete User Controller.
   * 
   * @param id String Object with the requested User id.
   * @return ResponseEntity Object with status 200 and a body with the deleted
   *         User.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<UserResponse> delete(@PathVariable("id") String id) {
    UserResponse removed = userService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(removed);
  }
}
