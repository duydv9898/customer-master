package com.backbase.customer_master.application.command.handler;

import com.backbase.customer_master.application.command.model.*;
import com.backbase.customer_master.common.exception.*;
import com.backbase.customer_master.domain.model.*;
import com.backbase.customer_master.domain.repository.*;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import com.backbase.customer_master.infrastructure.persistence.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerCommandHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    // Reference data repositories
    private final GenderRepository genderRepository;
    private final CountryRepository countryRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final ClientTypeRepository clientTypeRepository;
    private final CategoryRepository categoryRepository;
    private final OccupationRepository occupationRepository;
    private final IndustryRepository industryRepository;
    private final BusinessClassificationRepository businessClassificationRepository;
    private final EconomicSectorRepository economicSectorRepository;
    private final LanguageRepository languageRepository;
    private final ContactChannelRepository contactChannelRepository;
    private final CustomerSegmentRepository customerSegmentRepository;

    public CustomerDTO handle(CreateCustomerCommand command) {
        log.debug("Handling CreateCustomerCommand for: {}", command.getFullName());

        // Validate uniqueness
        if (command.getEmail() != null &&
                customerRepository.existsByEmailOrPrimaryPhone(command.getEmail(), command.getPrimaryPhone())) {
            throw new CustomerAlreadyExistsException("Customer already exists with email or phone number");
        }

        // Build customer entity
        Customer customer = Customer.builder()
                .customerId(UUID.randomUUID())
                .cifStatus(command.getCifStatus())
                .fullName(command.getFullName())
                .dateOfBirth(command.getDateOfBirth())
                .primaryPhone(command.getPrimaryPhone())
                .secondaryPhone(command.getSecondaryPhone())
                .email(command.getEmail())
                .jobTitle(command.getJobTitle())
                .monthlyIncome(command.getMonthlyIncome())
                .mainIncomeSource(command.getMainIncomeSource())
                .accountUsagePurpose(command.getAccountUsagePurpose())
                .internalClient(command.getInternalClient())
                .taxFileNo(command.getTaxFileNo())
                .taxable(command.getTaxable())
                .registrationChannel(command.getRegistrationChannel())
                .cifCreatedDate(command.getCifCreatedDate())
                .notes(command.getNotes())
                .customerClassification(command.getCustomerClassification())
                .versionNo(0)
                .createdAt(LocalDateTime.now())
                .createdBy(command.getCreatedBy())
                .updatedAt(LocalDateTime.now())
                .updatedBy(command.getCreatedBy())
                .sourceApp(command.getSourceApp())
                .correlationId(command.getCorrelationId())
                .build();

        // Set reference data
        setReferenceData(customer, command);

        // Save customer
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer created with ID: {}", savedCustomer.getCustomerId());
        return customerMapper.toDTO(savedCustomer);
    }

    public CustomerDTO handle(UpdateCustomerCommand command) {
        log.debug("Handling UpdateCustomerCommand for customer: {}", command.getCustomerId());

        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));

        try {
            // Update basic fields
            if (command.getFullName() != null) {
                customer.setFullName(command.getFullName());
            }
            if (command.getDateOfBirth() != null) {
                customer.setDateOfBirth(command.getDateOfBirth());
            }
            if (command.getSecondaryPhone() != null) {
                customer.setSecondaryPhone(command.getSecondaryPhone());
            }
            if (command.getEmail() != null) {
                customer.setEmail(command.getEmail());
            }
            if (command.getJobTitle() != null) {
                customer.setJobTitle(command.getJobTitle());
            }
            if (command.getMonthlyIncome() != null) {
                customer.setMonthlyIncome(command.getMonthlyIncome());
            }
            if (command.getMainIncomeSource() != null) {
                customer.setMainIncomeSource(command.getMainIncomeSource());
            }
            if (command.getAccountUsagePurpose() != null) {
                customer.setAccountUsagePurpose(command.getAccountUsagePurpose());
            }
            if (command.getTaxFileNo() != null) {
                customer.setTaxFileNo(command.getTaxFileNo());
            }
            if (command.getTaxable() != null) {
                customer.setTaxable(command.getTaxable());
            }
            if (command.getNotes() != null) {
                customer.setNotes(command.getNotes());
            }
            if (command.getCustomerClassification() != null) {
                customer.setCustomerClassification(command.getCustomerClassification());
            }

            // Update reference data
            updateReferenceData(customer, command);

            // Update metadata
            customer.setUpdatedAt(LocalDateTime.now());
            if (command.getUpdatedBy() != null) {
                customer.setUpdatedBy(command.getUpdatedBy());
            }
            if (command.getCorrelationId() != null) {
                customer.setCorrelationId(command.getCorrelationId());
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

    public CustomerDTO handle(UpdateCustomerStatusCommand command) {
        log.debug("Handling UpdateCustomerStatusCommand for customer: {}", command.getCustomerId());

        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));

        customer.updateStatus(command.getStatus());
        if (command.getLastModifiedBy() != null) {
            customer.setUpdatedBy(command.getLastModifiedBy());
        }

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer status updated to: {} for customer: {}", command.getStatus(), command.getCustomerId());
        return customerMapper.toDTO(savedCustomer);
    }

    public void handle(DeactivateCustomerCommand command) {
        log.debug("Handling DeactivateCustomerCommand for customer: {}", command.getCustomerId());

        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));

        customer.updateStatus("INACTIVE");
        if (command.getLastModifiedBy() != null) {
            customer.setUpdatedBy(command.getLastModifiedBy());
        }

        customerRepository.save(customer);

        log.info("Customer deactivated: {}", command.getCustomerId());
    }

    public void handle(DeleteCustomerCommand command) {
        log.warn("Handling DeleteCustomerCommand for customer: {}", command.getCustomerId());

        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId()));

        customerRepository.delete(customer);

        log.warn("Customer hard deleted: {}", command.getCustomerId());
    }

    // Helper methods
    private void setReferenceData(Customer customer, CreateCustomerCommand command) {
        // Set gender
        if (command.getGenderCode() != null) {
            Gender gender = genderRepository.findByGenderCode(command.getGenderCode())
                    .orElseThrow(() -> new ValidationException("Invalid gender code: " + command.getGenderCode()));
            customer.setGender(gender);
        }

        // Set nationality
        if (command.getNationalityCode() != null) {
            Country nationality = countryRepository.findByCountryCode(command.getNationalityCode())
                    .orElseThrow(() -> new ValidationException("Invalid nationality code: " + command.getNationalityCode()));
            customer.setNationality(nationality);
        }

        // Set marital status
        if (command.getMaritalStatusCode() != null) {
            MaritalStatus maritalStatus = maritalStatusRepository.findByMaritalStatusCode(command.getMaritalStatusCode())
                    .orElseThrow(() -> new ValidationException("Invalid marital status code: " + command.getMaritalStatusCode()));
            customer.setMaritalStatus(maritalStatus);
        }

        // Set client type
        if (command.getClientTypeCode() != null) {
            ClientType clientType = clientTypeRepository.findByClientTypeCode(command.getClientTypeCode())
                    .orElseThrow(() -> new ValidationException("Invalid client type code: " + command.getClientTypeCode()));
            customer.setClientType(clientType);
        }

        // Set category
        if (command.getCategoryCode() != null) {
            Category category = categoryRepository.findByCategoryCode(command.getCategoryCode())
                    .orElseThrow(() -> new ValidationException("Invalid category code: " + command.getCategoryCode()));
            customer.setCategory(category);
        }

        // Set occupation
        if (command.getOccupationCode() != null) {
            Occupation occupation = occupationRepository.findByOccupationCode(command.getOccupationCode())
                    .orElseThrow(() -> new ValidationException("Invalid occupation code: " + command.getOccupationCode()));
            customer.setOccupation(occupation);
        }

        // Set industry
        if (command.getIndustryCode() != null) {
            Industry industry = industryRepository.findByIndustryCode(command.getIndustryCode())
                    .orElseThrow(() -> new ValidationException("Invalid industry code: " + command.getIndustryCode()));
            customer.setClassificationIndustry(industry);
        }

        // Set business classification
        if (command.getBusinessClassCode() != null) {
            BusinessClassification businessClass = businessClassificationRepository.findByBusinessClassCode(command.getBusinessClassCode())
                    .orElseThrow(() -> new ValidationException("Invalid business class code: " + command.getBusinessClassCode()));
            customer.setClassificationBusiness(businessClass);
        }

        // Set economic sector
        if (command.getSectorCode() != null) {
            EconomicSector sector = economicSectorRepository.findBySectorCode(command.getSectorCode())
                    .orElseThrow(() -> new ValidationException("Invalid sector code: " + command.getSectorCode()));
            customer.setClassificationSector(sector);
        }

        // Set preferred language
        if (command.getPreferredLanguageCode() != null) {
            Language language = languageRepository.findByLanguageCode(command.getPreferredLanguageCode())
                    .orElseThrow(() -> new ValidationException("Invalid language code: " + command.getPreferredLanguageCode()));
            customer.setPreferredLanguage(language);
        }

        // Set contact channel
        if (command.getContactChannelCode() != null) {
            ContactChannel channel = contactChannelRepository.findByContactChannelCode(command.getContactChannelCode())
                    .orElseThrow(() -> new ValidationException("Invalid contact channel code: " + command.getContactChannelCode()));
            customer.setPreferredContactChannel(channel);
        }

        // Set customer segment
        if (command.getSegmentCode() != null) {
            CustomerSegment segment = customerSegmentRepository.findBySegmentCode(command.getSegmentCode())
                    .orElseThrow(() -> new ValidationException("Invalid segment code: " + command.getSegmentCode()));
            customer.setCustomerSegment(segment);
        }
    }

    private void updateReferenceData(Customer customer, UpdateCustomerCommand command) {
        // Update marital status
        if (command.getMaritalStatusCode() != null) {
            MaritalStatus maritalStatus = maritalStatusRepository.findByMaritalStatusCode(command.getMaritalStatusCode())
                    .orElseThrow(() -> new ValidationException("Invalid marital status code"));
            customer.setMaritalStatus(maritalStatus);
        }

        // Update occupation
        if (command.getOccupationCode() != null) {
            Occupation occupation = occupationRepository.findByOccupationCode(command.getOccupationCode())
                    .orElseThrow(() -> new ValidationException("Invalid occupation code"));
            customer.setOccupation(occupation);
        }

        // Update industry
        if (command.getIndustryCode() != null) {
            Industry industry = industryRepository.findByIndustryCode(command.getIndustryCode())
                    .orElseThrow(() -> new ValidationException("Invalid industry code"));
            customer.setClassificationIndustry(industry);
        }

        // Update business classification
        if (command.getBusinessClassCode() != null) {
            BusinessClassification businessClass = businessClassificationRepository.findByBusinessClassCode(command.getBusinessClassCode())
                    .orElseThrow(() -> new ValidationException("Invalid business class code"));
            customer.setClassificationBusiness(businessClass);
        }

        // Update economic sector
        if (command.getSectorCode() != null) {
            EconomicSector sector = economicSectorRepository.findBySectorCode(command.getSectorCode())
                    .orElseThrow(() -> new ValidationException("Invalid sector code"));
            customer.setClassificationSector(sector);
        }

        // Update preferred language
        if (command.getPreferredLanguageCode() != null) {
            Language language = languageRepository.findByLanguageCode(command.getPreferredLanguageCode())
                    .orElseThrow(() -> new ValidationException("Invalid language code"));
            customer.setPreferredLanguage(language);
        }

        // Update contact channel
        if (command.getContactChannelCode() != null) {
            ContactChannel channel = contactChannelRepository.findByContactChannelCode(command.getContactChannelCode())
                    .orElseThrow(() -> new ValidationException("Invalid contact channel code"));
            customer.setPreferredContactChannel(channel);
        }

        // Update customer segment
        if (command.getSegmentCode() != null) {
            CustomerSegment segment = customerSegmentRepository.findBySegmentCode(command.getSegmentCode())
                    .orElseThrow(() -> new ValidationException("Invalid segment code"));
            customer.setCustomerSegment(segment);
        }
    }
}