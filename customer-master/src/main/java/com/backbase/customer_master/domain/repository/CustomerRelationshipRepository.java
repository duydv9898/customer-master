package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.CustomerRelationship;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for CustomerRelationship entity
 */
public interface CustomerRelationshipRepository {
    
    CustomerRelationship save(CustomerRelationship relationship);
    
    Optional<CustomerRelationship> findById(String relationshipId);
    
    List<CustomerRelationship> findByCustomerCustomerId(String customerId);
    
    List<CustomerRelationship> findByRelatedCustomerId(String relatedCustomerId);
    
    List<CustomerRelationship> findByRelationshipType(String relationshipType);
    
    List<CustomerRelationship> findByRelationshipStatus(String relationshipStatus);
    
    void deleteById(String relationshipId);
    
    boolean existsById(String relationshipId);
}