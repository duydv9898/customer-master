package com.backbase.customer_master.application.query.service;

import com.backbase.customer_master.application.query.handler.CustomerQueryHandler;
import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Query Service for Customer operations (CQRS Pattern)
 * Acts as a facade/orchestrator for query operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryService {

    private final CustomerQueryHandler customerQueryHandler;

    /**
     * Get customer by ID
     */
    public CustomerDTO getCustomerById(String customerId) {
        log.debug("Processing get customer by ID query for: {}", customerId);
        
        GetCustomerByIdQuery query = GetCustomerByIdQuery.builder()
                .customerId(customerId)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customer basic info by ID
     */
    public CustomerDTO getCustomerBasicById(String customerId) {
        log.debug("Processing get customer basic info query for: {}", customerId);
        
        GetCustomerBasicByIdQuery query = GetCustomerBasicByIdQuery.builder()
                .customerId(customerId)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get all customers with pagination
     */
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        log.debug("Processing get all customers query");
        
        GetAllCustomersQuery query = GetAllCustomersQuery.builder()
                .pageable(pageable)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Search customers by name
     */
    public Page<CustomerDTO> searchCustomersByName(String name, Pageable pageable) {
        log.debug("Processing search customers by name query for: {}", name);
        
        SearchCustomersByNameQuery query = SearchCustomersByNameQuery.builder()
                .name(name)
                .pageable(pageable)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by branch
     */
    public Page<CustomerDTO> getCustomersByBranch(String branchId, Pageable pageable) {
        log.debug("Processing get customers by branch query for: {}", branchId);
        
        GetCustomersByBranchQuery query = GetCustomersByBranchQuery.builder()
                .branchId(branchId)
                .pageable(pageable)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by type
     */
    public Page<CustomerDTO> getCustomersByType(String customerType, Pageable pageable) {
        log.debug("Processing get customers by type query for: {}", customerType);
        
        GetCustomersByTypeQuery query = GetCustomersByTypeQuery.builder()
                .customerType(customerType)
                .pageable(pageable)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by status
     */
    public List<CustomerDTO> getCustomersByStatus(String status) {
        log.debug("Processing get customers by status query for: {}", status);
        
        GetCustomersByStatusQuery query = GetCustomersByStatusQuery.builder()
                .status(status)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Find customer by email
     */
    public Optional<CustomerDTO> findCustomerByEmail(String email) {
        log.debug("Processing find customer by email query for: {}", email);
        
        FindCustomerByEmailQuery query = FindCustomerByEmailQuery.builder()
                .email(email)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Find customer by phone number
     */
    public Optional<CustomerDTO> findCustomerByPhoneNumber(String phoneNumber) {
        log.debug("Processing find customer by phone query for: {}", phoneNumber);
        
        FindCustomerByPhoneNumberQuery query = FindCustomerByPhoneNumberQuery.builder()
                .phoneNumber(phoneNumber)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Find customer by identification number
     */
    public Optional<CustomerDTO> findCustomerByIdentificationNumber(String identificationNumber) {
        log.debug("Processing find customer by identification query");
        
        FindCustomerByIdentificationNumberQuery query = FindCustomerByIdentificationNumberQuery.builder()
                .identificationNumber(identificationNumber)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by date of birth range
     */
    public List<CustomerDTO> getCustomersByDateOfBirthRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Processing get customers by date of birth range query: {} to {}", startDate, endDate);
        
        GetCustomersByDateOfBirthRangeQuery query = GetCustomersByDateOfBirthRangeQuery.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get PEP customers
     */
//    public List<CustomerDTO> getPepCustomers() {
//        log.debug("Processing get PEP customers query");
//
//        GetPepCustomersQuery query = GetPepCustomersQuery.builder().build();
//
//        return customerQueryHandler.handle(query);
//    }
//
//    /**
//     * Get customers in sanctions list
//     */
//    public List<CustomerDTO> getSanctionsListCustomers() {
//        log.debug("Processing get sanctions list customers query");
//
//        GetSanctionsListCustomersQuery query = GetSanctionsListCustomersQuery.builder().build();
//
//        return customerQueryHandler.handle(query);
//    }

    /**
     * Get customers created in date range
     */
    public List<CustomerDTO> getCustomersCreatedInRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Processing get customers created in range query: {} to {}", startDate, endDate);
        
        GetCustomersCreatedInRangeQuery query = GetCustomersCreatedInRangeQuery.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by relationship manager
     */
    public List<CustomerDTO> getCustomersByRelationshipManager(String relationshipManagerId) {
        log.debug("Processing get customers by relationship manager query for: {}", relationshipManagerId);
        
        GetCustomersByRelationshipManagerQuery query = GetCustomersByRelationshipManagerQuery.builder()
                .relationshipManagerId(relationshipManagerId)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by KYC status
     */
    public List<CustomerDTO> getCustomersByKycStatus(String kycStatus) {
        log.debug("Processing get customers by KYC status query for: {}", kycStatus);
        
        GetCustomersByKycStatusQuery query = GetCustomersByKycStatusQuery.builder()
                .kycStatus(kycStatus)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by risk level
     */
    public List<CustomerDTO> getCustomersByRiskLevel(String riskLevel) {
        log.debug("Processing get customers by risk level query for: {}", riskLevel);
        
        GetCustomersByRiskLevelQuery query = GetCustomersByRiskLevelQuery.builder()
                .riskLevel(riskLevel)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Get customers by segment
     */
    public Page<CustomerDTO> getCustomersBySegment(String customerSegment, Pageable pageable) {
        log.debug("Processing get customers by segment query for: {}", customerSegment);
        
        GetCustomersBySegmentQuery query = GetCustomersBySegmentQuery.builder()
                .customerSegment(customerSegment)
                .pageable(pageable)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Count customers by status
     */
    public Long countCustomersByStatus(String status) {
        log.debug("Processing count customers by status query for: {}", status);
        
        CountCustomersByStatusQuery query = CountCustomersByStatusQuery.builder()
                .status(status)
                .build();
        
        return customerQueryHandler.handle(query);
    }

    /**
     * Check if customer exists
     */
    public Boolean customerExists(String customerId) {
        log.debug("Processing customer exists query for: {}", customerId);
        
        CustomerExistsQuery query = CustomerExistsQuery.builder()
                .customerId(customerId)
                .build();
        
        return customerQueryHandler.handle(query);
    }
}