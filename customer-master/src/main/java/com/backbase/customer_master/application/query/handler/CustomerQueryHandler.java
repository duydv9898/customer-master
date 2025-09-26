package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import com.backbase.customer_master.infrastructure.persistence.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Query Handler for Customer read operations (CQRS Pattern)
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerQueryHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Handle GetCustomerByIdQuery
     */
    public CustomerDTO handle(GetCustomerByIdQuery query) {
        log.debug("Handling GetCustomerByIdQuery for customer ID: {}", query.getCustomerId());
        
        Customer customer = findCustomerByIdOrThrow(query.getCustomerId());
        return customerMapper.toDTO(customer);
    }

    /**
     * Handle GetCustomerBasicByIdQuery
     */
    public CustomerDTO handle(GetCustomerBasicByIdQuery query) {
        log.debug("Handling GetCustomerBasicByIdQuery for customer ID: {}", query.getCustomerId());
        
        Customer customer = findCustomerByIdOrThrow(query.getCustomerId());
        return customerMapper.toBasicDTO(customer);
    }

    /**
     * Handle GetAllCustomersQuery
     */
    public Page<CustomerDTO> handle(GetAllCustomersQuery query) {
        log.debug("Handling GetAllCustomersQuery with pagination");
        
        Page<Customer> customers = customerRepository.findAll(query.getPageable());
        return customers.map(customerMapper::toSummaryDTO);
    }

    /**
     * Handle SearchCustomersByNameQuery
     */
    public Page<CustomerDTO> handle(SearchCustomersByNameQuery query) {
        log.debug("Handling SearchCustomersByNameQuery for name: {}", query.getName());
        
        Page<Customer> customers = customerRepository.findByFullNameContainingIgnoreCase(query.getName(), query.getPageable());
        return customers.map(customerMapper::toSummaryDTO);
    }

    /**
     * Handle GetCustomersByBranchQuery
     */
    public Page<CustomerDTO> handle(GetCustomersByBranchQuery query) {
        log.debug("Handling GetCustomersByBranchQuery for branch: {}", query.getBranchId());
        
        Page<Customer> customers = customerRepository.findByBranchId(query.getBranchId(), query.getPageable());
        return customers.map(customerMapper::toSummaryDTO);
    }

    /**
     * Handle GetCustomersByTypeQuery
     */
    public Page<CustomerDTO> handle(GetCustomersByTypeQuery query) {
        log.debug("Handling GetCustomersByTypeQuery for type: {}", query.getCustomerType());
        
        Page<Customer> customers = customerRepository.findByCustomerType(query.getCustomerType(), query.getPageable());
        return customers.map(customerMapper::toSummaryDTO);
    }

    /**
     * Handle GetCustomersByStatusQuery
     */
    public List<CustomerDTO> handle(GetCustomersByStatusQuery query) {
        log.debug("Handling GetCustomersByStatusQuery for status: {}", query.getStatus());
        
        List<Customer> customers = customerRepository.findByCifStatus(query.getStatus());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle FindCustomerByEmailQuery
     */
    public Optional<CustomerDTO> handle(FindCustomerByEmailQuery query) {
        log.debug("Handling FindCustomerByEmailQuery for email: {}", query.getEmail());
        
        Optional<Customer> customer = customerRepository.findByEmail(query.getEmail());
        return customer.map(customerMapper::toDTO);
    }

    /**
     * Handle FindCustomerByPhoneNumberQuery
     */
    public Optional<CustomerDTO> handle(FindCustomerByPhoneNumberQuery query) {
        log.debug("Handling FindCustomerByPhoneNumberQuery for phone: {}", query.getPhoneNumber());
        
        Optional<Customer> customer = customerRepository.findByPhoneNumber(query.getPhoneNumber());
        return customer.map(customerMapper::toDTO);
    }

    /**
     * Handle FindCustomerByIdentificationNumberQuery
     */
    public Optional<CustomerDTO> handle(FindCustomerByIdentificationNumberQuery query) {
        log.debug("Handling FindCustomerByIdentificationNumberQuery");
        
        Optional<Customer> customer = customerRepository.findByIdentificationNumber(query.getIdentificationNumber());
        return customer.map(customerMapper::toDTO);
    }

    /**
     * Handle GetCustomersByDateOfBirthRangeQuery
     */
    public List<CustomerDTO> handle(GetCustomersByDateOfBirthRangeQuery query) {
        log.debug("Handling GetCustomersByDateOfBirthRangeQuery from {} to {}", 
                 query.getStartDate(), query.getEndDate());
        
        List<Customer> customers = customerRepository.findByDateOfBirthBetween(query.getStartDate(), query.getEndDate());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle GetPepCustomersQuery
     */
//    public List<CustomerDTO> handle(GetPepCustomersQuery query) {
//        log.debug("Handling GetPepCustomersQuery");
//
//        List<Customer> customers = customerRepository.findPepCustomers();
//        return customerMapper.toDTOList(customers);
//    }
//
//    /**
//     * Handle GetSanctionsListCustomersQuery
//     */
//    public List<CustomerDTO> handle(GetSanctionsListCustomersQuery query) {
//        log.debug("Handling GetSanctionsListCustomersQuery");
//
//        List<Customer> customers = customerRepository.findSanctionsListCustomers();
//        return customerMapper.toDTOList(customers);
//    }

    /**
     * Handle GetCustomersCreatedInRangeQuery
     */
    public List<CustomerDTO> handle(GetCustomersCreatedInRangeQuery query) {
        log.debug("Handling GetCustomersCreatedInRangeQuery from {} to {}", 
                 query.getStartDate(), query.getEndDate());
        
        List<Customer> customers = customerRepository.findByCreatedDateBetween(query.getStartDate(), query.getEndDate());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle GetCustomersByRelationshipManagerQuery
     */
    public List<CustomerDTO> handle(GetCustomersByRelationshipManagerQuery query) {
        log.debug("Handling GetCustomersByRelationshipManagerQuery for RM: {}", query.getRelationshipManagerId());
        
        List<Customer> customers = customerRepository.findByRelationshipManagerId(query.getRelationshipManagerId());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle GetCustomersByKycStatusQuery
     */
    public List<CustomerDTO> handle(GetCustomersByKycStatusQuery query) {
        log.debug("Handling GetCustomersByKycStatusQuery for status: {}", query.getKycStatus());
        
        List<Customer> customers = customerRepository.findByKycStatus(query.getKycStatus());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle GetCustomersByRiskLevelQuery
     */
    public List<CustomerDTO> handle(GetCustomersByRiskLevelQuery query) {
        log.debug("Handling GetCustomersByRiskLevelQuery for risk level: {}", query.getRiskLevel());
        
        List<Customer> customers = customerRepository.findByRiskLevel(query.getRiskLevel());
        return customerMapper.toDTOList(customers);
    }

    /**
     * Handle GetCustomersBySegmentQuery
     */
    public Page<CustomerDTO> handle(GetCustomersBySegmentQuery query) {
        log.debug("Handling GetCustomersBySegmentQuery for segment: {}", query.getCustomerSegment());
        
        Page<Customer> customers = customerRepository.findByCustomerSegment(query.getCustomerSegment(), query.getPageable());
        return customers.map(customerMapper::toSummaryDTO);
    }

    /**
     * Handle CountCustomersByStatusQuery
     */
    public Long handle(CountCustomersByStatusQuery query) {
        log.debug("Handling CountCustomersByStatusQuery for status: {}", query.getStatus());
        
        return customerRepository.countByStatus(query.getStatus());
    }

    /**
     * Handle CustomerExistsQuery
     */
    public Boolean handle(CustomerExistsQuery query) {
        log.debug("Handling CustomerExistsQuery for customer: {}", query.getCustomerId());
        
        return customerRepository.existsById(query.getCustomerId());
    }

    // Private helper methods

    private Customer findCustomerByIdOrThrow(String customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
    }
}