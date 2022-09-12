package com.trybe.dronefeeder.contract.response;

import com.trybe.dronefeeder.model.DroneModel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DroneResponse {
  private UUID id;
  private String name;
  private String latitude;
  private String longitude;
  private LocalDate lastMaintenance;
  private List<DeliveryResponse> deliveries;

  /** DroneResponse constructor using DroneModel. */
  public DroneResponse(DroneModel model) {
    this.id = model.getId();
    this.name = model.getName();
    this.latitude = model.getLatitude();
    this.longitude = model.getLongitude();
    this.lastMaintenance = model.getLastMaintenance();
    this.deliveries = model.getDeliveries().stream()
        .map(DeliveryResponse::new).collect(Collectors.toList());
  }

  public void addDelivery(DeliveryResponse delivery) {
    deliveries.add(delivery);
  }

  public void removeDelivery(DeliveryResponse delivery) {
    deliveries.remove(delivery);
  }

}
