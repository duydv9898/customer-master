package com.backbase.customer_master.infrastructure.persistence.jpa;

import com.backbase.customer_master.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, String> {
    
    Optional<Customer> findByCustomerId(String customerId);
    
    List<Customer> findByFullNameContainingIgnoreCase(String fullName);
    
    List<Customer> findByEmailIgnoreCase(String email);
    
    List<Customer> findByPhoneNumber(String phoneNumber);
    
    Page<Customer> findByBranchId(String branchId, Pageable pageable);
    
    Page<Customer> findByCifStatus(String cifStatus, Pageable pageable);
    
    @Query("SELECT c FROM Customer c WHERE " +
           "(:customerId IS NULL OR LOWER(c.customerId) LIKE LOWER(CONCAT('%', :customerId, '%'))) AND " +
           "(:fullName IS NULL OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')))")
    Page<Customer> findByCustomerIdContainingIgnoreCaseOrFullNameContainingIgnoreCase(
        @Param("customerId") String customerId,
        @Param("fullName") String fullName,
        Pageable pageable
    );
    
    @Query("SELECT c FROM Customer c WHERE c.cifStatus = 'ACTIVE'")
    List<Customer> findActiveCustomers();
    
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.addresses WHERE c.customerId = :customerId")
    Optional<Customer> findByIdWithAddresses(@Param("customerId") String customerId);
    
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.identifications WHERE c.customerId = :customerId")
    Optional<Customer> findByIdWithIdentifications(@Param("customerId") String customerId);
    
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.relationships WHERE c.customerId = :customerId")
    Optional<Customer> findByIdWithRelationships(@Param("customerId") String customerId);
    
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.products WHERE c.customerId = :customerId")
    Optional<Customer> findByIdWithProducts(@Param("customerId") String customerId);
}
