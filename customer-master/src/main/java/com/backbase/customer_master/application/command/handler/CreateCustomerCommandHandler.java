package com.backbase.customer_master.application.command.handler;

import com.backbase.customer_master.application.command.model.CreateCustomerCommand;
import com.backbase.customer_master.presentation.dto.generated.model.CustomerResponse;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.application.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handler for CreateCustomerCommand
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCustomerCommandHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse handle(CreateCustomerCommand command) {
        log.info("Handling CreateCustomerCommand for: {}", command.getFullName());

        // Create customer entity from command
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
            .branchId(command.getBranchId())
            .customerSegment(command.getCustomerSegment())
            .relationshipManagerId(command.getRelationshipManagerId())
            .taxId(command.getTaxId())
            .sourceOfFund(command.getSourceOfFund())
            .referralSource(command.getReferralSource())
            .languagePreference(command.getLanguagePreference())
            .communicationPreference(command.getCommunicationPreference())
            .isConsentMarketing(command.getIsConsentMarketing())
            .notes(command.getNotes())
            .build();

        // Save customer
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer created successfully with ID: {}", savedCustomer.getCustomerId());

        // Convert to response DTO using mapper
        return customerMapper.toResponse(savedCustomer);
    }
}