package com.trybe.dronefeeder.contract.response;

import com.trybe.dronefeeder.model.AddressModel;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AddressResponse {

  private UUID id;

  private String country;

  private String state;

  private String city;

  private String addressLine;

  private String other;

  private String userId;

  /**
   * AddressResponse constructor using AddressModel.
   * 
   * @param model AddressModel object to reveive attributes from.
   */
  public AddressResponse(AddressModel model) {
    this.id = model.getId();
    this.country = model.getCountry();
    this.state = model.getState();
    this.city = model.getCity();
    this.addressLine = model.getAddressLine();
    this.other = model.getOther();
    this.userId = model.getUser().getId();
  }

}
