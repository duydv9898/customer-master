package com.backbase.customer_master.application.command.handler;

import com.backbase.customer_master.application.command.model.*;
import com.backbase.customer_master.common.exception.*;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import com.backbase.customer_master.infrastructure.persistence.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Command Handler for Customer operations (CQRS Pattern)
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerCommandHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Handle CreateCustomerCommand
     */
    public CustomerDTO handle(CreateCustomerCommand command) {
        log.debug("Handling CreateCustomerCommand for email: {}", command.getEmail());
        
        // Validate if customer already exists
        if (command.getEmail() != null && 
            customerRepository.existsByEmailOrPhoneNumber(command.getEmail(), command.getPhoneNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with email or phone number");
        }
        
        // Create customer entity
        Customer customer = Customer.builder()
                .customerType(command.getCustomerType())
                .fullName(command.getFullName())
                .firstName(command.getFirstName())
                .middleName(command.getMiddleName())
                .lastName(command.getLastName())
                .dateOfBirth(command.getDateOfBirth())
                .placeOfBirth(command.getPlaceOfBirth())
                .genderId(command.getGenderId())
                .maritalStatusId(command.getMaritalStatusId())
                .nationalityId(command.getNationalityId())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .secondaryPhone(command.getSecondaryPhone())
                .occupation(command.getOccupation())
                .educationLevel(command.getEducationLevel())
                .incomeRange(command.getIncomeRange())
                .riskLevel(command.getRiskLevel())
                .isPep(command.getIsPep() != null ? command.getIsPep() : false)
                .isSanctionsList(command.getIsSanctionsList() != null ? command.getIsSanctionsList() : false)
                .kycStatus(command.getKycStatus())
                .branchId(command.getBranchId())
                .customerSegment(command.getCustomerSegment())
                .relationshipManagerId(command.getRelationshipManagerId())
                .taxId(command.getTaxId())
                .sourceOfFund(command.getSourceOfFund())
                .referralSource(command.getReferralSource())
                .languagePreference(command.getLanguagePreference())
                .communicationPreference(command.getCommunicationPreference())
                .isConsentMarketing(command.getIsConsentMarketing() != null ? command.getIsConsentMarketing() : false)
                .consentDate(command.getConsentDate())
                .notes(command.getNotes())
                .createdBy(command.getCreatedBy())
                .build();

        // Save customer
        Customer savedCustomer = customerRepository.save(customer);
        
        log.info("Customer created with ID: {}", savedCustomer.getCustomerId());
        return customerMapper.toDTO(savedCustomer);
    }

    /**
     * Handle UpdateCustomerCommand
     */
    public CustomerDTO handle(UpdateCustomerCommand command) {
        log.debug("Handling UpdateCustomerCommand for customer: {}", command.getCustomerId());
        
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));
        
        try {
            // Update customer fields
            if (command.getFullName() != null) {
                customer.setFullName(command.getFullName());
            }
            if (command.getFirstName() != null) {
                customer.setFirstName(command.getFirstName());
            }
            if (command.getMiddleName() != null) {
                customer.setMiddleName(command.getMiddleName());
            }
            if (command.getLastName() != null) {
                customer.setLastName(command.getLastName());
            }
            if (command.getDateOfBirth() != null) {
                customer.setDateOfBirth(command.getDateOfBirth());
            }
            if (command.getPlaceOfBirth() != null) {
                customer.setPlaceOfBirth(command.getPlaceOfBirth());
            }
            if (command.getMaritalStatusId() != null) {
                customer.setMaritalStatusId(command.getMaritalStatusId());
            }
            if (command.getEmail() != null) {
                customer.setEmail(command.getEmail());
            }
            if (command.getPhoneNumber() != null) {
                customer.setPhoneNumber(command.getPhoneNumber());
            }
            if (command.getSecondaryPhone() != null) {
                customer.setSecondaryPhone(command.getSecondaryPhone());
            }
            if (command.getOccupation() != null) {
                customer.setOccupation(command.getOccupation());
            }
            if (command.getEducationLevel() != null) {
                customer.setEducationLevel(command.getEducationLevel());
            }
            if (command.getIncomeRange() != null) {
                customer.setIncomeRange(command.getIncomeRange());
            }
            if (command.getRiskLevel() != null) {
                customer.setRiskLevel(command.getRiskLevel());
            }
            if (command.getIsPep() != null) {
                customer.setIsPep(command.getIsPep());
            }
            if (command.getIsSanctionsList() != null) {
                customer.setIsSanctionsList(command.getIsSanctionsList());
            }
            if (command.getKycStatus() != null) {
                customer.setKycStatus(command.getKycStatus());
            }
            if (command.getCustomerSegment() != null) {
                customer.setCustomerSegment(command.getCustomerSegment());
            }
            if (command.getRelationshipManagerId() != null) {
                customer.setRelationshipManagerId(command.getRelationshipManagerId());
            }
            if (command.getTaxId() != null) {
                customer.setTaxId(command.getTaxId());
            }
            if (command.getSourceOfFund() != null) {
                customer.setSourceOfFund(command.getSourceOfFund());
            }
            if (command.getReferralSource() != null) {
                customer.setReferralSource(command.getReferralSource());
            }
            if (command.getLanguagePreference() != null) {
                customer.setLanguagePreference(command.getLanguagePreference());
            }
            if (command.getCommunicationPreference() != null) {
                customer.setCommunicationPreference(command.getCommunicationPreference());
            }
            if (command.getIsConsentMarketing() != null) {
                customer.setIsConsentMarketing(command.getIsConsentMarketing());
            }
            if (command.getConsentDate() != null) {
                customer.setConsentDate(command.getConsentDate());
            }
            if (command.getNotes() != null) {
                customer.setNotes(command.getNotes());
            }
            if (command.getLastModifiedBy() != null) {
                customer.setLastModifiedBy(command.getLastModifiedBy());
            }
            
            // Check version for optimistic locking
            if (command.getVersionNo() != null && !command.getVersionNo().equals(customer.getVersionNo())) {
                throw new OptimisticLockingException("Customer has been modified by another user");
            }

            Customer savedCustomer = customerRepository.save(customer);
            
            log.info("Customer updated: {}", savedCustomer.getCustomerId());
            return customerMapper.toDTO(savedCustomer);
            
        } catch (OptimisticLockingFailureException ex) {
            throw new OptimisticLockingException("Customer has been modified by another user", ex);
        }
    }

    /**
     * Handle UpdateCustomerStatusCommand
     */
    public CustomerDTO handle(UpdateCustomerStatusCommand command) {
        log.debug("Handling UpdateCustomerStatusCommand for customer: {}", command.getCustomerId());
        
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));
        
        customer.updateStatus(command.getStatus());
        if (command.getLastModifiedBy() != null) {
            customer.setLastModifiedBy(command.getLastModifiedBy());
        }
        
        Customer savedCustomer = customerRepository.save(customer);
        
        log.info("Customer status updated to: {} for customer: {}", command.getStatus(), command.getCustomerId());
        return customerMapper.toDTO(savedCustomer);
    }

    /**
     * Handle DeactivateCustomerCommand
     */
    public void handle(DeactivateCustomerCommand command) {
        log.debug("Handling DeactivateCustomerCommand for customer: {}", command.getCustomerId());
        
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));
        
        customer.updateStatus("INACTIVE");
        if (command.getLastModifiedBy() != null) {
            customer.setLastModifiedBy(command.getLastModifiedBy());
        }
        
        customerRepository.save(customer);
        
        log.info("Customer deactivated: {}", command.getCustomerId());
    }

    /**
     * Handle DeleteCustomerCommand
     */
    public void handle(DeleteCustomerCommand command) {
        log.warn("Handling DeleteCustomerCommand for customer: {}", command.getCustomerId());
        
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));
        
        customerRepository.delete(customer);
        
        log.warn("Customer hard deleted: {}", command.getCustomerId());
    }
}