package com.trybe.dronefeeder.contract.response;

import com.trybe.dronefeeder.model.DeliveryModel;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DeliveryResponse {

  private UUID id;

  private UUID deliveryDroneId;

  private Boolean wasDelivered;

  private UserResponse sender;

  private UserResponse receiver;

  private List<PackageResponse> packages;

  /**
   * DeliveryResponse constructor using DeliveryModel.
   * 
   * @param model DeliveryModel object to reveive attributes from.
   */
  public DeliveryResponse(DeliveryModel model) {
    this.id = model.getId();
    this.deliveryDroneId = model.getDrone().getId();
    this.wasDelivered = model.getWasDelivered();
    this.sender = new UserResponse(model.getSender());
    this.receiver = new UserResponse(model.getReceiver());
    this.packages = model.getPackages().stream()
        .map(PackageResponse::new).collect(Collectors.toList());
  }

}
