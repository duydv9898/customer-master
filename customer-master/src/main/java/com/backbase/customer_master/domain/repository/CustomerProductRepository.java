package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.CustomerProduct;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for CustomerProduct entity
 */
public interface CustomerProductRepository {
    
    CustomerProduct save(CustomerProduct product);
    
    Optional<CustomerProduct> findById(String customerProductId);
    
    List<CustomerProduct> findByCustomerCustomerId(String customerId);
    
    List<CustomerProduct> findByProductType(String productType);
    
    List<CustomerProduct> findByProductStatus(String productStatus);
    
    Optional<CustomerProduct> findByAccountNumber(String accountNumber);
    
    List<CustomerProduct> findByCustomerCustomerIdAndIsPrimary(String customerId, Boolean isPrimary);
    
    void deleteById(String customerProductId);
    
    boolean existsById(String customerProductId);
}