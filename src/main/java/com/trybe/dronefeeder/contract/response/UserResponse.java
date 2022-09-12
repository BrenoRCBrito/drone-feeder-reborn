package com.trybe.dronefeeder.contract.response;

import com.trybe.dronefeeder.model.AddressModel;
import com.trybe.dronefeeder.model.DeliveryModel;
import com.trybe.dronefeeder.model.UserModel;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserResponse {

  private String id;

  private String firstName;

  private String lastName;

  private List<AddressResponse> addresses;

  private List<DeliveryResponse> sentDeliveries;

  private List<DeliveryResponse> receivedDeliveries;

  /**
   * UserResponse constructor using UserModel.
   * 
   * @param model UserModel object to reveive attributes from.
   */
  public UserResponse(UserModel model) {
    this.id = model.getId();
    this.firstName = model.getFirstName();
    this.lastName = model.getLastName();
    List<AddressModel> modelAddresses = model.getAddresses();
    List<DeliveryModel> modelSentDeliveries = model.getSentDeliveries();
    List<DeliveryModel> modelReceivedDeliveries = model.getReceivedDeliveries();
    if (modelAddresses != null && modelSentDeliveries != null && modelReceivedDeliveries != null) {
      this.addresses = model.getAddresses().stream()
          .map(AddressResponse::new).collect(Collectors.toList());
      this.sentDeliveries = model.getSentDeliveries().stream()
          .map(DeliveryResponse::new).collect(Collectors.toList());
      this.receivedDeliveries = model.getReceivedDeliveries().stream()
          .map(DeliveryResponse::new).collect(Collectors.toList());
    }
  }

}
