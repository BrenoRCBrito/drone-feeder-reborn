package com.trybe.dronefeeder.contract.requests;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PackageRequest {
  
  @NotBlank(message = "DeliveryId cannot be empty or null")
  private UUID deliveryId;

  @NotBlank(message = "Content cannot be empty or null")
  private String content;

  @NotBlank(message = "SendDate cannot be empty or null")
  @PastOrPresent(message = "SendDate cannot be a future date")
  private LocalDate sendDate;

  @NotBlank(message = "ReceiptDate cannot be empty or null")
  @FutureOrPresent(message = "ReceiptDate cannot be a past date.")
  private LocalDate receiptDate;

}
