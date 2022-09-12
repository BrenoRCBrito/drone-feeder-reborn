package com.trybe.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AddressModel {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String country;

  private String state;

  private String city;

  private String addressLine;

  private String other;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserModel user;

}
