package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.BusinessClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Business Classification Repository
@Repository
public interface BusinessClassificationRepository extends JpaRepository<BusinessClassification, UUID> {
    Optional<BusinessClassification> findByBusinessClassCode(String businessClassCode);
}
