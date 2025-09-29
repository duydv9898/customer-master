package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Category Repository
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByCategoryCode(String categoryCode);
}
