package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Marital Status Repository
@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, UUID> {
    Optional<MaritalStatus> findByMaritalStatusCode(String maritalStatusCode);
}
