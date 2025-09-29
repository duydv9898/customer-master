package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Industry Repository
@Repository
public interface IndustryRepository extends JpaRepository<Industry, UUID> {
    Optional<Industry> findByIndustryCode(String industryCode);
}
