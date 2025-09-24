package com.backbase.customer_master.application.command.handler;

import com.backbase.customer_master.application.command.model.UpdateCustomerCommand;
import com.backbase.customer_master.presentation.dto.CustomerDto;
import com.backbase.customer_master.presentation.dto.generated.model.CustomerResponse;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.application.mapper.CustomerMapper;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import com.backbase.customer_master.common.exception.OptimisticLockingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Handler for UpdateCustomerCommand
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCustomerCommandHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse handle(UpdateCustomerCommand command) {
        log.info("Handling UpdateCustomerCommand for ID: {}", command.getCustomerId());

        // Find existing customer
        Optional<Customer> existingCustomer = customerRepository.findById(command.getCustomerId());
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId());
        }

        Customer customer = existingCustomer.get();

        // Check version for optimistic locking
        if (!customer.getVersionNo().equals(command.getVersionNo())) {
            throw new OptimisticLockingException("Customer has been modified by another user");
        }

        // Update customer fields using mapper
        customerMapper.updateEntity(customer, convertToUpdateRequest(command));

        try {
            // Save updated customer
            Customer updatedCustomer = customerRepository.save(customer);
            
            log.info("Customer updated successfully: {}", command.getCustomerId());

            return customerMapper.toResponse(updatedCustomer);
            
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new OptimisticLockingException("Customer has been modified by another user", e);
        }
    }

    private CustomerDto.UpdateRequest convertToUpdateRequest(UpdateCustomerCommand command) {
        return CustomerDto.UpdateRequest.builder()
            .fullName(command.getFullName())
            .firstName(command.getFirstName())
            .middleName(command.getMiddleName())
            .lastName(command.getLastName())
            .placeOfBirth(command.getPlaceOfBirth())
            .maritalStatusId(command.getMaritalStatusId())
            .email(command.getEmail())
            .phoneNumber(command.getPhoneNumber())
            .secondaryPhone(command.getSecondaryPhone())
            .occupation(command.getOccupation())
            .educationLevel(command.getEducationLevel())
            .incomeRange(command.getIncomeRange())
            .customerSegment(command.getCustomerSegment())
            .relationshipManagerId(command.getRelationshipManagerId())
            .taxId(command.getTaxId())
            .sourceOfFund(command.getSourceOfFund())
            .referralSource(command.getReferralSource())
            .languagePreference(command.getLanguagePreference())
            .communicationPreference(command.getCommunicationPreference())
            .isConsentMarketing(command.getIsConsentMarketing())
            .notes(command.getNotes())
            .versionNo(command.getVersionNo())
            .build();
    }
}