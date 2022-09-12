package com.trybe.dronefeeder.repository;

import com.trybe.dronefeeder.model.PackageModel;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageModel, UUID> {
}
