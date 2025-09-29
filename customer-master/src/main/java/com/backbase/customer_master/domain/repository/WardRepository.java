package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Ward Repository
@Repository
public interface WardRepository extends JpaRepository<Ward, UUID> {
    Optional<Ward> findByWardCode(String wardCode);
}
