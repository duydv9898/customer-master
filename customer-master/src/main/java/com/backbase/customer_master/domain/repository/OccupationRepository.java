package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Occupation Repository
@Repository
public interface OccupationRepository extends JpaRepository<Occupation, UUID> {
    Optional<Occupation> findByOccupationCode(String occupationCode);
}
