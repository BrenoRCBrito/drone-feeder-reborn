package com.trybe.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserModel {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "user", 
      cascade = CascadeType.ALL, 
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<AddressModel> addresses;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "sender", 
      cascade = CascadeType.ALL, 
      orphanRemoval = true, 
      fetch = FetchType.LAZY)
  private List<DeliveryModel> sentDeliveries;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "receiver", 
      cascade = CascadeType.ALL, 
      orphanRemoval = true, 
      fetch = FetchType.LAZY)
  private List<DeliveryModel> receivedDeliveries;

  public void addAddress(AddressModel address) {
    addresses.add(address);
  }

  public void removeAddress(AddressModel address) {
    addresses.remove(address);
  }

  public void addSentDelivery(DeliveryModel delivery) {
    sentDeliveries.add(delivery);
  }

  public void removeSentDelivery(DeliveryModel delivery) {
    sentDeliveries.remove(delivery);
  }

  public void addReceivedDelivery(DeliveryModel delivery) {
    receivedDeliveries.add(delivery);
  }

  public void removeReceivedDelivery(DeliveryModel delivery) {
    receivedDeliveries.remove(delivery);
  }

}
