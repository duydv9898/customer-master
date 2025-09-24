package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for Customer entity
 */
public interface CustomerRepository {
    
    Customer save(Customer customer);
    
    Optional<Customer> findById(String customerId);
    
    Optional<Customer> findByIdWithRelations(String customerId, 
                                           boolean includeAddresses,
                                           boolean includeIdentifications, 
                                           boolean includeRelationships,
                                           boolean includeProducts);
    
    Page<Customer> findAll(Pageable pageable);
    
    List<Customer> findByFullNameContainingIgnoreCase(String fullName);
    
    List<Customer> findByEmailIgnoreCase(String email);
    
    List<Customer> findByPhoneNumber(String phoneNumber);
    
    Page<Customer> findByBranchId(String branchId, Pageable pageable);
    
    Page<Customer> findByCifStatus(String cifStatus, Pageable pageable);
    
    Page<Customer> findByCustomerIdContainingIgnoreCaseOrFullNameContainingIgnoreCase(
        String customerId, String fullName, Pageable pageable);
    
    List<Customer> findActiveCustomers();
    
    void deleteById(String customerId);
    
    boolean existsById(String customerId);
    
    long count();
}