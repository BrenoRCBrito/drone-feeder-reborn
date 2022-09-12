package com.trybe.dronefeeder.contract.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AddressRequest {

  @NotBlank(message = "UserId cannot be empty or null")
  private String userId;

  @NotBlank(message = "Country cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country must only contain spaces and letters")
  private String country;

  @NotBlank(message = "State cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State must only contain spaces and letters")
  private String state;

  @NotBlank(message = "City cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must only contain spaces and letters")
  private String city;

  @NotBlank(message = "AddressLine cannot be empty or null")
  @Pattern(
      regexp = "^[a-zA-Z0-9à-úÀ-Ú,\\s]+$", 
      message = "AddressLine must only contain spaces, letters, digits and commas")
  private String addressLine;

  private String other;

}
