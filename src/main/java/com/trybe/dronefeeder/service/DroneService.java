package com.trybe.dronefeeder.service;

import com.trybe.dronefeeder.contract.requests.DroneRequest;
import com.trybe.dronefeeder.contract.response.DroneResponse;
import com.trybe.dronefeeder.model.DroneModel;
import com.trybe.dronefeeder.repository.DroneRepository;
import com.trybe.dronefeeder.validations.ValidateBody;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

  @Autowired
  private DroneRepository droneRepository;

  /**
   * Method that transfers the data from the request
   * to the new model or the model that will be updated.
   * 
   * @param model   DroneModel Object.
   * @param request DroneRequest Object.
   */
  public void setDroneAttributes(DroneModel model, DroneRequest request) {
    model.setLatitude(ValidateBody.latitude(request.getLatitude()));
    model.setLongitude(ValidateBody.longitude(request.getLongitude()));
    model.setLastMaintenance(ValidateBody.date(request.getLastMaintenance()));
    model.setName(request.getName());
  }

  /**
   * Method that query all the DroneModel entities,
   * maps them to DroneResponse and returns it.
   * 
   * @return a list of DroneResponse objects.
   */
  public List<DroneResponse> findAll() {
    return droneRepository.findAll().stream().map(DroneResponse::new).collect(Collectors.toList());
  }

  /**
   * Method that creates an DroneModel entity from the DroneRequest object
   * and returns it as a DroneResponse Object.
   * 
   * @param request DroneRequest Object.
   * @return a DroneResposne Object.
   */
  public DroneResponse create(DroneRequest request) {
    DroneModel droneModel = new DroneModel();
    setDroneAttributes(droneModel, request);
    return new DroneResponse(droneRepository.save(droneModel));
  }

  /**
   * Method that query the DroneModel entity that has the requested id
   * and returns it as a DroneResponse Object.
   * 
   * @param id a Long object.
   * @return a DroneResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DroneResponse findById(UUID id) {
    return droneRepository.findById(id).map(DroneResponse::new)
        .orElseThrow(() -> new NotFoundException("No id was found"));
  }

  /**
   * Method that updates an DroneModel entity from the DroneRequest object
   * and returns it as a DroneResponse Object.
   * 
   * @param request DroneRequest Object.
   * @param id      a Long object.
   * @return a DroneResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DroneResponse update(DroneRequest request, UUID id) {
    return droneRepository.findById(id).map(toUpdate -> {
      setDroneAttributes(toUpdate, request);
      return new DroneResponse(droneRepository.save(toUpdate));
    }).orElseThrow(() -> new NotFoundException(
        "Can't update, the provided id does not exist"));
  }

  /**
   * Method that deletes an DroneModel entity from the DroneRequest object
   * and returns it as a DroneResponse Object.
   * 
   * @param id a Long object.
   * @return a DroneResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DroneResponse delete(UUID id) {
    return droneRepository.findById(id).map(toDelete -> {
      droneRepository.deleteById(id);
      return new DroneResponse(toDelete);
    }).orElseThrow(
        () -> new NotFoundException(
            "Can't delete, the provided id does not exist"));
  }
}