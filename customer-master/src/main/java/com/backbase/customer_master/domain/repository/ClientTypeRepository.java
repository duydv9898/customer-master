package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Client Type Repository
@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, UUID> {
    Optional<ClientType> findByClientTypeCode(String clientTypeCode);
}
