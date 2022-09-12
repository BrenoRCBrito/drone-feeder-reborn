package com.trybe.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drones")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DroneModel {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;
  
  private String latitude;

  private String longitude;

  private LocalDate lastMaintenance;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "drone",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<DeliveryModel> deliveries;

  public void addDelivery(DeliveryModel delivery) {
    deliveries.add(delivery);
  }

  public void removeDelivery(DeliveryModel delivery) {
    deliveries.remove(delivery);
  }

}