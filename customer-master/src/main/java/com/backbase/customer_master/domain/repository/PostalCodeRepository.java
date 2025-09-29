package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Postal Code Repository
@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, UUID> {
    Optional<PostalCode> findByPostalCode(String postalCode);
}
