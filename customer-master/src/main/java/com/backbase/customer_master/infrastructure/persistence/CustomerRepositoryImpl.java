package com.backbase.customer_master.infrastructure.persistence;

import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.infrastructure.persistence.jpa.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    @Override
    public Customer save(Customer customer) {
        return jpaCustomerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return jpaCustomerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> findByIdWithRelations(String customerId, 
                                                  boolean includeAddresses,
                                                  boolean includeIdentifications, 
                                                  boolean includeRelationships,
                                                  boolean includeProducts) {
        // For simplicity, fetch all relations if any are requested
        // In a real implementation, you might want to create specific queries
        if (includeAddresses || includeIdentifications || includeRelationships || includeProducts) {
            return jpaCustomerRepository.findById(customerId);
        }
        return jpaCustomerRepository.findById(customerId);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return jpaCustomerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findByFullNameContainingIgnoreCase(String fullName) {
        return jpaCustomerRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    @Override
    public List<Customer> findByEmailIgnoreCase(String email) {
        return jpaCustomerRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<Customer> findByPhoneNumber(String phoneNumber) {
        return jpaCustomerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Page<Customer> findByBranchId(String branchId, Pageable pageable) {
        return jpaCustomerRepository.findByBranchId(branchId, pageable);
    }

    @Override
    public Page<Customer> findByCifStatus(String cifStatus, Pageable pageable) {
        return jpaCustomerRepository.findByCifStatus(cifStatus, pageable);
    }

    @Override
    public Page<Customer> findByCustomerIdContainingIgnoreCaseOrFullNameContainingIgnoreCase(
            String customerId, String fullName, Pageable pageable) {
        return jpaCustomerRepository.findByCustomerIdContainingIgnoreCaseOrFullNameContainingIgnoreCase(
            customerId, fullName, pageable);
    }

    @Override
    public List<Customer> findActiveCustomers() {
        return jpaCustomerRepository.findActiveCustomers();
    }

    @Override
    public void deleteById(String customerId) {
        jpaCustomerRepository.deleteById(customerId);
    }

    @Override
    public boolean existsById(String customerId) {
        return jpaCustomerRepository.existsById(customerId);
    }

    @Override
    public long count() {
        return jpaCustomerRepository.count();
    }
}