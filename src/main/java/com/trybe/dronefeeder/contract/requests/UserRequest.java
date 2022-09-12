package com.trybe.dronefeeder.contract.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserRequest {

  @NotBlank(message = "UserId cannot be empty or null")
  private String id;

  @NotBlank(message = "FirstName cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "FirstName must only contain spaces and letters")
  private String firstName;

  @NotBlank(message = "LastName cannot be empty or null")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "LastName must only contain spaces and letters")
  private String lastName;

}
