package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Identification;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for Identification entity
 */
public interface IdentificationRepository {
    
    Identification save(Identification identification);
    
    Optional<Identification> findById(String identificationId);
    
    List<Identification> findByCustomerCustomerId(String customerId);
    
    List<Identification> findByCustomerCustomerIdAndIsActive(String customerId, Boolean isActive);
    
    Optional<Identification> findByCustomerCustomerIdAndIsDefault(String customerId, Boolean isDefault);
    
    List<Identification> findByIdentificationTypeId(String identificationTypeId);
    
    Optional<Identification> findByIdentificationNumber(String identificationNumber);
    
    List<Identification> findByIsVerified(Boolean isVerified);
    
    void deleteById(String identificationId);
    
    boolean existsById(String identificationId);
}