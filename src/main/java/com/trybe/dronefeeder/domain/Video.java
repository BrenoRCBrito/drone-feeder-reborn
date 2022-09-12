package com.trybe.dronefeeder.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Getter
@Setter
@EqualsAndHashCode
public class Video {

  String fileName;

  String downloadUrl;

  public Video(String fileName) {
    this.fileName = fileName;
  }

  public void setDownloadUrl(String fileName) {
    String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    this.downloadUrl = baseUrl + "/video/download/" + fileName;
  }

}
