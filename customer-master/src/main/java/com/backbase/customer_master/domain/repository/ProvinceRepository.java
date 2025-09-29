package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Province Repository
@Repository
public interface ProvinceRepository extends JpaRepository<Province, UUID> {
    Optional<Province> findByProvinceCode(String provinceCode);
}
