package com.trybe.dronefeeder.service;

import com.trybe.dronefeeder.contract.requests.UserRequest;
import com.trybe.dronefeeder.contract.response.UserResponse;
import com.trybe.dronefeeder.model.UserModel;
import com.trybe.dronefeeder.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Method that transfers the data from the request
   * to the new model or the one that will be updated.
   * 
   * @param model   UserModel Object.
   * @param request UserRequest Object.
   * @throws NotFoundException if the Ids from the request doesn't
   *                           exist.
   */
  public void setUserAttributes(UserModel model, UserRequest request) {
    model.setId(request.getId());
    model.setFirstName(request.getFirstName());
    model.setLastName(request.getLastName());
  }

  /**
   * Method that query all the UserModel entities,
   * maps them to UserResponse and returns it.
   * 
   * @return a list of UserResponse objects.
   */
  @Transactional
  public List<UserResponse> findAll() {
    return userRepository.findAll().stream()
        .map(UserResponse::new).collect(Collectors.toList());
  }

  /**
   * Method that creates an UserModel entity from the UserRequest object
   * and returns it as a UserResponse Object.
   * 
   * @param request UserRequest Object.
   * @return a UserResposne Object.
   */
  public UserResponse create(UserRequest request) {
    UserModel userModel = new UserModel();
    setUserAttributes(userModel, request);
    return new UserResponse(userRepository.save(userModel));
  }

  /**
   * Method that query the UserModel entity that has the requested id
   * and returns it as a UserResponse Object.
   * 
   * @param id a Long object.
   * @return a UserResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public UserResponse findById(String id) {
    return userRepository.findById(id).map(UserResponse::new)
        .orElseThrow(() -> new NotFoundException("The provided id doesn't exist"));
  }

  /**
   * Method that updates an UserModel entity from the UserRequest object
   * and returns it as a UserResponse Object.
   * 
   * @param request UserRequest Object.
   * @param id      a Long object.
   * @return a UserResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public UserResponse update(UserRequest request, String id) {
    return userRepository.findById(id).map(toUpdate -> {
      setUserAttributes(toUpdate, request);
      return new UserResponse(toUpdate);
    }).orElseThrow(
        () -> new NotFoundException("Can't update, the provided id doesn't exist"));
  }

  /**
   * Method that deletes an UserModel entity from the UserRequest object
   * and returns it as a UserResponse Object.
   * 
   * @param id a Long object.
   * @return a UserResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public UserResponse delete(String id) {
    return userRepository.findById(id).map(toDelete -> {
      userRepository.deleteById(id);
      return new UserResponse(toDelete);
    }).orElseThrow(
        () -> new NotFoundException("Can't delete, the provided id doesn't exist"));
  }

}
