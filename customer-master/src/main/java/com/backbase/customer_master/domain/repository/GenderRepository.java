package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Gender Repository
@Repository
public interface GenderRepository extends JpaRepository<Gender, UUID> {
    Optional<Gender> findByGenderCode(String genderCode);
}

