package com.trybe.dronefeeder.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError extends GenericError {

  private List<FieldError> fieldErrors = new ArrayList<>();

  public ValidationError(String message) {
    super(message);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((fieldErrors == null) ? 0 : fieldErrors.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ValidationError other = (ValidationError) obj;
    if (fieldErrors == null) {
      if (other.fieldErrors != null) {
        return false;
      }
    } else if (!fieldErrors.equals(other.fieldErrors)) {
      return false;
    }
    return true;
  }

  public void addFieldError(String field, String message) {
    fieldErrors.add(new FieldError(field, message));
  }

  public void removeFieldError(String field, String message) {
    fieldErrors.remove(new FieldError(field, message));
  }

  @Getter
  @Setter
  @AllArgsConstructor
  static class FieldError {
    private String field;

    private String message;

  }

}
