package com.trybe.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DeliveryModel {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Type(type = "true_false")
  private Boolean wasDelivered;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "delivery",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<PackageModel> packages;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "sender_id")
  private UserModel sender;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private UserModel receiver;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "drone_id")
  private DroneModel drone;
  
  public void addPackage(PackageModel pkg) {
    this.packages.add(pkg);
  }

  public void removePackage(PackageModel pkg) {
    this.packages.remove(pkg);
  }

}