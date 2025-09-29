package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.ResidencyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Residency Status Repository
@Repository
public interface ResidencyStatusRepository extends JpaRepository<ResidencyStatus, UUID> {
    Optional<ResidencyStatus> findByResidencyStatusCode(String residencyStatusCode);
}
