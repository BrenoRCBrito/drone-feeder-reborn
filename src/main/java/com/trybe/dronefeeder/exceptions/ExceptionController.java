package com.trybe.dronefeeder.exceptions;

import java.util.List;

import javax.ws.rs.ClientErrorException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionController {
  /**
   * API Generic Exception Handler.
   * 
   * @param exception the Exception object caught.
   * @return a ResponseEntity object with an erros status and a error custom
   *         message.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericError> handleExceptions(
      Exception exception) {
    int status = 500;
    if (exception instanceof ClientErrorException) {
      status = ((ClientErrorException) exception).getResponse().getStatus();
    }
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(status)
        .body(new GenericError(exception.getMessage()));
  }

  /**
   * Body Validation Exception Handler.
   * 
   * @param exception MethodArgumentNotValidException thrown by invalid request
   *                  objects.
   * @return an error response listing the body errors.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationError handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
    ValidationError response = new ValidationError("Body fields are incorrect");
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    for (FieldError error : fieldErrors) {
      log.error(error.getDefaultMessage());
      response.addFieldError(error.getField(), error.getDefaultMessage());
    }
    return response;
  }

}
