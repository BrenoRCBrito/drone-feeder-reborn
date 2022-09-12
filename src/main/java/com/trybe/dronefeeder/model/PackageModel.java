package com.trybe.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
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
@Table(name = "packages")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PackageModel {
  
  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String content;

  private UUID trackingCode = UUID.randomUUID();

  private LocalDate sendDate;

  private LocalDate receiptDate;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "delivery_id")
  private DeliveryModel delivery;

}
