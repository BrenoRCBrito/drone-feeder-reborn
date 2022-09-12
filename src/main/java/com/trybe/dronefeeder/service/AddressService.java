package com.trybe.dronefeeder.service;

import com.trybe.dronefeeder.contract.requests.AddressRequest;
import com.trybe.dronefeeder.contract.response.AddressResponse;
import com.trybe.dronefeeder.model.AddressModel;
import com.trybe.dronefeeder.model.UserModel;
import com.trybe.dronefeeder.repository.AddressRepository;
import com.trybe.dronefeeder.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AddressRepository addressRepository;

  /**
   * Method that transfers the data from the request
   * to the new model or the one that will be updated.
   * 
   * @param model   AddressModel Object.
   * @param request AddressRequest Object.
   * @throws NotFoundException if the Ids from the request doesn't
   *                           exist.
   */
  public void setAddressAttributes(AddressModel model, AddressRequest request) {
    Optional<UserModel> userModel = userRepository.findById(request.getUserId());
    model.setUser(userModel.orElseThrow(
        () -> new NotFoundException("The provided Address User id doesn't exist")));
    model.setAddressLine(request.getAddressLine());
    model.setCity(request.getCity());
    model.setCountry(request.getCountry());
    model.setOther(request.getOther());
    model.setState(request.getState());
  }

  /**
   * Method that query all the AddressModel entities,
   * maps them to AddressResponse and returns it.
   * 
   * @return a list of AddressResponse objects.
   */
  public List<AddressResponse> findAll() {
    return addressRepository.findAll().stream()
        .map(AddressResponse::new).collect(Collectors.toList());
  }

  /**
   * Method that creates an AddressModel entity from the AddressRequest object
   * and returns it as a AddressResponse Object.
   * 
   * @param request AddressRequest Object.
   * @return a AddressResposne Object.
   */
  public AddressResponse create(AddressRequest request) {
    AddressModel addressModel = new AddressModel();
    setAddressAttributes(addressModel, request);
    return new AddressResponse(addressRepository.save(addressModel));
  }

  /**
   * Method that query the AddressModel entity that has the requested id
   * and returns it as a AddressResponse Object.
   * 
   * @param id a Long object.
   * @return a AddressResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public AddressResponse findById(UUID id) {
    return addressRepository.findById(id).map(AddressResponse::new)
        .orElseThrow(() -> new NotFoundException("The provided id doesn't exist"));
  }

  /**
   * Method that updates an AddressModel entity from the AddressRequest object
   * and returns it as a AddressResponse Object.
   * 
   * @param request AddressRequest Object.
   * @param id      a Long object.
   * @return a AddressResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public AddressResponse update(AddressRequest request, UUID id) {
    return addressRepository.findById(id).map(toUpdate -> {
      setAddressAttributes(toUpdate, request);
      return new AddressResponse(toUpdate);
    }).orElseThrow(
        () -> new NotFoundException("Can't update, the provided id doesn't exist"));
  }

  /**
   * Method that deletes an AddressModel entity from the AddressRequest object
   * and returns it as a AddressResponse Object.
   * 
   * @param id a Long object.
   * @return a AddressResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public AddressResponse delete(UUID id) {
    return addressRepository.findById(id).map(toDelete -> {
      addressRepository.deleteById(id);
      return new AddressResponse(toDelete);
    }).orElseThrow(
        () -> new NotFoundException("Can't delete, the provided id doesn't exist"));
  }

}
