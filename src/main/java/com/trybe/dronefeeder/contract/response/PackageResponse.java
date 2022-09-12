package com.trybe.dronefeeder.contract.response;

import com.trybe.dronefeeder.model.PackageModel;
import java.time.LocalDate;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PackageResponse {

  private UUID id;

  private UUID trackingCode = UUID.randomUUID();

  private UUID deliveryId;

  private String content;

  private LocalDate sendDate;

  private LocalDate receiptDate;

  /**
   * PackageResponse constructor using PackageModel.
   * 
   * @param model PackageModel object to reveive attributes from.
   */
  public PackageResponse(PackageModel model) {
    this.id = model.getId();
    this.trackingCode = model.getTrackingCode();
    this.deliveryId = model.getDelivery().getId();
    this.content = model.getContent();
    this.sendDate = model.getSendDate();
    this.receiptDate = model.getReceiptDate();
  }

}
