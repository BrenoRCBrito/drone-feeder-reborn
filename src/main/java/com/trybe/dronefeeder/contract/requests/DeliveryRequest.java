package com.trybe.dronefeeder.contract.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DeliveryRequest {

  @NotBlank(message = "DeliveryDroneId cannot be empty or null")
  private UUID deliveryDroneId;

  @NotBlank(message = "WasDelivered cannot be empty or null")
  @Pattern(regexp = "^(true|false)$", message = "WasDelivered must be true or false")
  private Boolean wasDelivered;

  @NotBlank(message = "SenderId cannot be empty or null")
  private String senderId;

  @NotBlank(message = "ReceiverId cannot be empty or null")
  private String receiverId;

}
