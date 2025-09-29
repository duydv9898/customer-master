package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Country Repository
@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    Optional<Country> findByCountryCode(String countryCode);
}
