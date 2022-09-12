package com.trybe.dronefeeder.service;

import com.trybe.dronefeeder.contract.requests.PackageRequest;
import com.trybe.dronefeeder.contract.response.PackageResponse;
import com.trybe.dronefeeder.model.DeliveryModel;
import com.trybe.dronefeeder.model.PackageModel;
import com.trybe.dronefeeder.repository.DeliveryRepository;
import com.trybe.dronefeeder.repository.PackageRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private PackageRepository packageRepository;

  /**
   * Method that transfers the data from the request
   * to the new model or the one that will be updated.
   * 
   * @param model   PackageModel Object.
   * @param request PackageRequest Object.
   * @throws NotFoundException if the Ids from the request doesn't
   *                           exist.
   */
  public void setPackageAttributes(PackageModel model, PackageRequest request) {
    Optional<DeliveryModel> deliveryModel = deliveryRepository.findById(request.getDeliveryId());
    model.setDelivery(deliveryModel.orElseThrow(
        () -> new NotFoundException("The provided Delivery id doesn't exist")));
    model.setContent(request.getContent());
    model.setReceiptDate(request.getReceiptDate());
    model.setSendDate(request.getSendDate());
  }

  /**
   * Method that query all the PackageModel entities,
   * maps them to PackageResponse and returns it.
   * 
   * @return a list of PackageResponse objects.
   */
  public List<PackageResponse> findAll() {
    return packageRepository.findAll().stream()
        .map(PackageResponse::new).collect(Collectors.toList());
  }

  /**
   * Method that creates an PackageModel entity from the PackageRequest object
   * and returns it as a PackageResponse Object.
   * 
   * @param request PackageRequest Object.
   * @return a PackageResposne Object.
   */
  public PackageResponse create(PackageRequest request) {
    PackageModel packageModel = new PackageModel();
    setPackageAttributes(packageModel, request);
    return new PackageResponse(packageRepository.save(packageModel));
  }

  /**
   * Method that query the PackageModel entity that has the requested id
   * and returns it as a PackageResponse Object.
   * 
   * @param id a Long object.
   * @return a PackageResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public PackageResponse findById(UUID id) {
    return packageRepository.findById(id).map(PackageResponse::new)
        .orElseThrow(() -> new NotFoundException("The provided id doesn't exist"));
  }

  /**
   * Method that updates an PackageModel entity from the PackageRequest object
   * and returns it as a PackageResponse Object.
   * 
   * @param request PackageRequest Object.
   * @param id      a Long object.
   * @return a PackageResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public PackageResponse update(PackageRequest request, UUID id) {
    return packageRepository.findById(id).map(toUpdate -> {
      setPackageAttributes(toUpdate, request);
      return new PackageResponse(toUpdate);
    }).orElseThrow(
        () -> new NotFoundException("Can't update, the provided id doesn't exist"));
  }

  /**
   * Method that deletes an PackageModel entity from the PackageRequest object
   * and returns it as a PackageResponse Object.
   * 
   * @param id a Long object.
   * @return a PackageResposne Object.
   * @throws NotFoundException if the requested id was not found.
   */
  public PackageResponse delete(UUID id) {
    return packageRepository.findById(id).map(toDelete -> {
      packageRepository.deleteById(id);
      return new PackageResponse(toDelete);
    }).orElseThrow(
        () -> new NotFoundException("Can't delete, the provided id doesn't exist"));
  }

}
