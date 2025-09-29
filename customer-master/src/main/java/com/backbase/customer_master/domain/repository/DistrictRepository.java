package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// District Repository
@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    Optional<District> findByDistrictCode(String districtCode);
}
