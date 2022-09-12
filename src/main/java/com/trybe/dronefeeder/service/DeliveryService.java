package com.trybe.dronefeeder.service;

import com.trybe.dronefeeder.contract.requests.DeliveryRequest;
import com.trybe.dronefeeder.contract.response.DeliveryResponse;
import com.trybe.dronefeeder.model.DeliveryModel;
import com.trybe.dronefeeder.model.DroneModel;
import com.trybe.dronefeeder.model.UserModel;
import com.trybe.dronefeeder.repository.DeliveryRepository;
import com.trybe.dronefeeder.repository.DroneRepository;
import com.trybe.dronefeeder.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DroneRepository droneRepository;

  @Autowired
  private UserRepository userRepository;

  /**
   * Method that transfers the data from the request
   * to the new model or the one that will be updated.
   * 
   * @param model   DeliveryModel Object.
   * @param request DeliveryRequest Object.
   * @throws NotFoundException if the Ids from the request doesn't
   *                           exist.
   */
  public void setDeliveryAttributes(DeliveryModel model, DeliveryRequest request) {
    Optional<DroneModel> droneModel = droneRepository.findById(request.getDeliveryDroneId());
    Optional<UserModel> senderUserModel = userRepository.findById(request.getSenderId());
    Optional<UserModel> receiverUserModel = userRepository.findById(request.getReceiverId());
    model.setDrone(droneModel.orElseThrow(
        () -> new NotFoundException("The provided Delivery Drone id doesn't exist")));
    model.setSender(senderUserModel.orElseThrow(
        () -> new NotFoundException("The provided Sender id doesn't exist")));
    model.setReceiver(receiverUserModel.orElseThrow(
        () -> new NotFoundException("The provided Receiver id doesn't exist")));
    model.setWasDelivered(request.getWasDelivered());
  }

  /**
   * Method that query all the DeliveryModel entities,
   * maps them to DeliveryResponse and returns it.
   * 
   * @return a list of DeliveryResponse objects.
   */
  public List<DeliveryResponse> findAll() {
    return deliveryRepository.findAll().stream()
        .map(DeliveryResponse::new).collect(Collectors.toList());
  }

  /**
   * Method that creates an DeliveryModel entity from the DeliveryRequest object
   * and returns it as a DeliveryResponse Object.
   * 
   * @param request DeliveryRequest Object.
   * @return a DeliveryResposne Object.
   */
  public DeliveryResponse create(DeliveryRequest request) {
    DeliveryModel deliveryModel = new DeliveryModel();
    setDeliveryAttributes(deliveryModel, request);
    return new DeliveryResponse(deliveryRepository.save(deliveryModel));
  }

  /**
   * Method that query the DeliveryModel entity that has the requested id
   * and returns it as a DeliveryResponse Object.
   * 
   * @param id a Long object.
   * @return a DeliveryResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DeliveryResponse findById(UUID id) {
    return deliveryRepository.findById(id).map(DeliveryResponse::new)
        .orElseThrow(() -> new NotFoundException("The provided id doesn't exist"));
  }

  /**
   * Method that updates an DeliveryModel entity from the DeliveryRequest object
   * and returns it as a DeliveryResponse Object.
   * 
   * @param request DeliveryRequest Object.
   * @param id      a Long object.
   * @return a DeliveryResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DeliveryResponse update(DeliveryRequest request, UUID id) {
    return deliveryRepository.findById(id).map(toUpdate -> {
      setDeliveryAttributes(toUpdate, request);
      return new DeliveryResponse(toUpdate);
    }).orElseThrow(
        () -> new NotFoundException("Can't update, the provided id doesn't exist"));
  }

  /**
   * Method that deletes an DeliveryModel entity from the DeliveryRequest object
   * and returns it as a DeliveryResponse Object.
   * 
   * @param id a Long object.
   * @return a DeliveryResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public DeliveryResponse delete(UUID id) {
    return deliveryRepository.findById(id).map(toDelete -> {
      deliveryRepository.deleteById(id);
      return new DeliveryResponse(toDelete);
    }).orElseThrow(
        () -> new NotFoundException("Can't delete, the provided id doesn't exist"));
  }
}