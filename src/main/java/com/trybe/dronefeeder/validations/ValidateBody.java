package com.trybe.dronefeeder.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.BadRequestException;

public class ValidateBody {

  private ValidateBody() {
  }

  /**
   * Validate a drone latitude and returns it.
   * 
   * @param latitude it's a drone latitude String value.
   * @return the latitude String.
   * @throws BadRequestException if the String is invalid.
   */
  public static String latitude(String latitude) {
    String latitudeRegex = "^(\\-?([0-8]?\\d(\\.\\d+)?|90(.0+)?))$";
    if (!latitude.matches(latitudeRegex)) {
      throw new BadRequestException("The request latitude is wrong");
    }
    return latitude;
  }

  /**
   * Validate a drone longitude and returns it.
   * 
   * @param longitude it's a drone longitude String value.
   * @return the longitude String.
   * @throws BadRequestException if the String is invalid.
   */
  public static String longitude(String longitude) {
    String longitudeRegex = "(\\-?(1?[0-7]?\\d(\\.\\d+)?|180((.0+)?)))$";
    if (!longitude.matches(longitudeRegex)) {
      throw new BadRequestException("The request longitude is wrong");
    }
    return longitude;
  }

  /**
   * Validate a drone lastMaintenance string and returns it parsed to LocalDate.
   * 
   * @param lastMaintenance it's a date as a String value.
   * @return the LocalDate version of the String.
   * @throws BadRequestException if the string is a invalid LocalDate.
   */
  public static LocalDate date(String lastMaintenance) {
    LocalDate parsedDate;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      parsedDate = LocalDate.parse(lastMaintenance, formatter);
    } catch (Exception e) {
      throw new BadRequestException("The request date is not valid");
    }
    if (!parsedDate.isAfter(LocalDate.of(2022, 07, 14))) {
      throw new BadRequestException("The request date is not valid");
    }
    return parsedDate;
  }

}
