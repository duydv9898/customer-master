package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Product Group Repository
@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, UUID> {
    Optional<ProductGroup> findByProductGroupCode(String productGroupCode);
}
