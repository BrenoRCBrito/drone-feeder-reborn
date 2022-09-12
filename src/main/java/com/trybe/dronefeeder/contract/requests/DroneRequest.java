package com.trybe.dronefeeder.contract.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DroneRequest {

  @NotBlank(message = "Name cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must only contain spaces and letters")
  private String name;

  @NotBlank(message = "Latitude cannot be empty or null")
  @Pattern(
      regexp = "^(\\-?([0-8]?\\d(\\.\\d+)?|90(.0+)?))$",
      message = "Latitude must be a number between -90 and 90")
  private String latitude;

  @NotBlank(message = "Longitude cannot be empty or null")
  @Pattern(
      regexp = "(\\-?(1?[0-7]?\\d(\\.\\d+)?|180((.0+)?)))$",
      message = "Latitude must be a number between -180 and 180")
  private String longitude;
  
  @NotBlank(message = "LastMaintenance cannot be empty or null")
  @PastOrPresent(message = "LastMaintenance cannot be a future date")
  private String lastMaintenance;

}
