package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.EconomicSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Economic Sector Repository
@Repository
public interface EconomicSectorRepository extends JpaRepository<EconomicSector, UUID> {
    Optional<EconomicSector> findBySectorCode(String sectorCode);
}
