package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.RelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Relationship Type Repository
@Repository
public interface RelationshipTypeRepository extends JpaRepository<RelationshipType, UUID> {
    Optional<RelationshipType> findByRelationshipTypeCode(String relationshipTypeCode);
}
