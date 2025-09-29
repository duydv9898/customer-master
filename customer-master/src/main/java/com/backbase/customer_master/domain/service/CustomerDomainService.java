package com.backbase.customer_master.domain.service;

import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.infrastructure.persistence.mapper.CustomerMapper;
import com.backbase.customer_master.infrastructure.persistence.specification.CustomerSpecifications;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerDomainService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Find customer by ID with all related data
     */
    public Optional<CustomerDTO> findCustomerById(UUID customerId) {
        log.debug("Finding customer by ID: {}", customerId);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasCustomerId(customerId))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by ID with basic data
     */
    public Optional<CustomerDTO> findCustomerBasicById(UUID customerId) {
        log.debug("Finding customer basic info by ID: {}", customerId);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasCustomerId(customerId))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toBasicDTO);
    }

    /**
     * Find customer by email
     */
    public Optional<CustomerDTO> findCustomerByEmail(String email) {
        log.debug("Finding customer by email");

        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasEmail(email.toLowerCase()))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by phone number
     */
    public Optional<CustomerDTO> findCustomerByPhoneNumber(String phoneNumber) {
        log.debug("Finding customer by phone number");

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return Optional.empty();
        }

        String normalizedPhone = normalizePhoneNumber(phoneNumber);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasPrimaryPhone(normalizedPhone))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by identification number
     */
    public Optional<CustomerDTO> findCustomerByIdentificationNumber(String identificationNumber) {
        log.debug("Finding customer by identification number");

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasIdentificationNumber(identificationNumber))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by tax file number
     */
    public Optional<CustomerDTO> findCustomerByTaxFileNo(String taxFileNo) {
        log.debug("Finding customer by tax file number");

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasTaxFileNo(taxFileNo))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Get all customers with pagination
     */
    public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
        log.debug("Finding all customers with pagination");

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by CIF status
     */
    public Page<CustomerDTO> findCustomersByStatus(String status, Pageable pageable) {
        log.debug("Finding customers by status: {}", status);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasCifStatus(status));

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by client type
     */
    public Page<CustomerDTO> findCustomersByType(String clientTypeCode, Pageable pageable) {
        log.debug("Finding customers by client type: {}", clientTypeCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasClientTypeCode(clientTypeCode))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Search customers by name
     */
    public Page<CustomerDTO> searchCustomersByName(String name, Pageable pageable) {
        log.debug("Searching customers by name: {}", name);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasFullNameContaining(name))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by segment
     */
    public Page<CustomerDTO> findCustomersBySegment(String segmentCode, Pageable pageable) {
        log.debug("Finding customers by segment: {}", segmentCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasSegmentCode(segmentCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by category
     */
    public Page<CustomerDTO> findCustomersByCategory(String categoryCode, Pageable pageable) {
        log.debug("Finding customers by category: {}", categoryCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasCategoryCode(categoryCode))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by date of birth range
     */
    public List<CustomerDTO> findCustomersByDateOfBirthRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Finding customers by date of birth range: {} to {}", startDate, endDate);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasDateOfBirthBetween(startDate, endDate))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by occupation
     */
    public List<CustomerDTO> findCustomersByOccupation(String occupationCode) {
        log.debug("Finding customers by occupation: {}", occupationCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasOccupationCode(occupationCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by industry
     */
    public List<CustomerDTO> findCustomersByIndustry(String industryCode) {
        log.debug("Finding customers by industry: {}", industryCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasIndustryCode(industryCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by business classification
     */
    public List<CustomerDTO> findCustomersByBusinessClass(String businessClassCode) {
        log.debug("Finding customers by business classification: {}", businessClassCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasBusinessClassCode(businessClassCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by economic sector
     */
    public List<CustomerDTO> findCustomersBySector(String sectorCode) {
        log.debug("Finding customers by sector: {}", sectorCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasSectorCode(sectorCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by language preference
     */
    public List<CustomerDTO> findCustomersByLanguage(String languageCode) {
        log.debug("Finding customers by language: {}", languageCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasLanguageCode(languageCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by contact channel
     */
    public List<CustomerDTO> findCustomersByContactChannel(String contactChannelCode) {
        log.debug("Finding customers by contact channel: {}", contactChannelCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasContactChannelCode(contactChannelCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by monthly income
     */
    public List<CustomerDTO> findCustomersByMonthlyIncome(String monthlyIncome) {
        log.debug("Finding customers by monthly income: {}", monthlyIncome);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasMonthlyIncome(monthlyIncome))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by registration channel
     */
    public List<CustomerDTO> findCustomersByRegistrationChannel(String registrationChannel) {
        log.debug("Finding customers by registration channel: {}", registrationChannel);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasRegistrationChannel(registrationChannel))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find internal clients
     */
    public List<CustomerDTO> findInternalClients() {
        log.debug("Finding internal clients");

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.isInternalClient("Y"))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find taxable customers
     */
    public List<CustomerDTO> findTaxableCustomers() {
        log.debug("Finding taxable customers");

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.isTaxable("Y"))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers created in date range
     */
    public List<CustomerDTO> findCustomersCreatedInRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Finding customers created between: {} and {}", startDate, endDate);

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.createdBetween(startDateTime, endDateTime))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by CIF created date range
     */
    public List<CustomerDTO> findCustomersByCifCreatedDateRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Finding customers by CIF created date: {} to {}", startDate, endDate);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasCifCreatedDateBetween(startDate, endDate))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by province
     */
    public List<CustomerDTO> findCustomersByProvince(String provinceCode) {
        log.debug("Finding customers by province: {}", provinceCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasAddressInProvince(provinceCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by district
     */
    public List<CustomerDTO> findCustomersByDistrict(String districtCode) {
        log.debug("Finding customers by district: {}", districtCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasAddressInDistrict(districtCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by product type
     */
    public List<CustomerDTO> findCustomersByProductType(String productType) {
        log.debug("Finding customers by product type: {}", productType);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasProductType(productType))
                .and(CustomerSpecifications.isActive());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by source application
     */
    public List<CustomerDTO> findCustomersBySourceApp(String sourceApp) {
        log.debug("Finding customers by source app: {}", sourceApp);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasSourceApp(sourceApp))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by correlation ID
     */
    public List<CustomerDTO> findCustomersByCorrelationId(String correlationId) {
        log.debug("Finding customers by correlation ID: {}", correlationId);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasCorrelationId(correlationId));

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers for periodic review
     */
    public List<CustomerDTO> findCustomersForPeriodicReview(LocalDateTime reviewDate) {
        log.debug("Finding customers for periodic review since: {}", reviewDate);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.isActive())
                .and(Specification.anyOf(
                        Specification.where((root, query, cb) -> cb.isNull(root.get("updatedAt"))),
                        CustomerSpecifications.modifiedAfter(reviewDate)
                ));

        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Complex search with multiple criteria
     */
    public Page<CustomerDTO> findCustomersWithCriteria(
            String status,
            String segment,
            String clientType,
            String category,
            Pageable pageable) {

        log.debug("Finding customers with criteria - Status: {}, Segment: {}, ClientType: {}, Category: {}",
                status, segment, clientType, category);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.excludeClosed());

        if (status != null) {
            spec = spec.and(CustomerSpecifications.hasCifStatus(status));
        }
        if (segment != null) {
            spec = spec.and(CustomerSpecifications.hasSegmentCode(segment));
        }
        if (clientType != null) {
            spec = spec.and(CustomerSpecifications.hasClientTypeCode(clientType));
        }
        if (category != null) {
            spec = spec.and(CustomerSpecifications.hasCategoryCode(category));
        }

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Complex text search
     */
    public Page<CustomerDTO> complexSearch(String searchTerm, Pageable pageable) {
        log.debug("Performing complex search with term: {}", searchTerm);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.complexSearch(searchTerm))
                .and(CustomerSpecifications.excludeClosed());

        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    // Count and existence methods

    /**
     * Count customers by status
     */
    public Long countCustomersByStatus(String status) {
        log.debug("Counting customers by status: {}", status);

        Specification<Customer> spec = CustomerSpecifications.hasCifStatus(status);

        return customerRepository.count(spec);
    }

    /**
     * Count customers by client type
     */
    public Long countCustomersByClientType(String clientTypeCode) {
        log.debug("Counting customers by client type: {}", clientTypeCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.hasClientTypeCode(clientTypeCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.count(spec);
    }

    /**
     * Count customers by segment
     */
    public Long countCustomersBySegment(String segmentCode) {
        log.debug("Counting customers by segment: {}", segmentCode);

        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.hasSegmentCode(segmentCode))
                .and(CustomerSpecifications.isActive());

        return customerRepository.count(spec);
    }

    /**
     * Check if customer exists by ID
     */
    public Boolean customerExists(UUID customerId) {
        log.debug("Checking if customer exists: {}", customerId);

        Specification<Customer> spec = CustomerSpecifications.hasCustomerId(customerId);

        return customerRepository.count(spec) > 0;
    }

    /**
     * Check if customer exists by email
     */
    public Boolean customerExistsByEmail(String email) {
        log.debug("Checking if customer exists by email");

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        Specification<Customer> spec = CustomerSpecifications.hasEmail(email.toLowerCase());

        return customerRepository.count(spec) > 0;
    }

    /**
     * Check if customer exists by phone number
     */
    public Boolean customerExistsByPhoneNumber(String phoneNumber) {
        log.debug("Checking if customer exists by phone number");

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }

        String normalizedPhone = normalizePhoneNumber(phoneNumber);
        Specification<Customer> spec = CustomerSpecifications.hasPrimaryPhone(normalizedPhone);

        return customerRepository.count(spec) > 0;
    }

    /**
     * Check if customer exists by tax file number
     */
    public Boolean customerExistsByTaxFileNo(String taxFileNo) {
        log.debug("Checking if customer exists by tax file number");

        if (taxFileNo == null || taxFileNo.trim().isEmpty()) {
            return false;
        }

        Specification<Customer> spec = CustomerSpecifications.hasTaxFileNo(taxFileNo);

        return customerRepository.count(spec) > 0;
    }

    /**
     * Get customer statistics
     */
    public CustomerStatistics getCustomerStatistics() {
        log.debug("Getting customer statistics");

        long totalActive = countCustomersByStatus("ACTIVE");
        long totalInactive = countCustomersByStatus("INACTIVE");
        long totalSuspended = countCustomersByStatus("SUSPENDED");
        long totalClosed = countCustomersByStatus("CLOSED");

        long internalClients = customerRepository.count(
                Specification.where(CustomerSpecifications.isInternalClient("Y"))
                        .and(CustomerSpecifications.isActive())
        );

        long taxableCustomers = customerRepository.count(
                Specification.where(CustomerSpecifications.isTaxable("Y"))
                        .and(CustomerSpecifications.isActive())
        );

        return CustomerStatistics.builder()
                .totalActive(totalActive)
                .totalInactive(totalInactive)
                .totalSuspended(totalSuspended)
                .totalClosed(totalClosed)
                .internalClients(internalClients)
                .taxableCustomers(taxableCustomers)
                .build();
    }

    // Helper methods

    private String normalizePhoneNumber(String phone) {
        if (phone == null) return null;
        String normalized = phone.replaceAll("[^0-9+]", "");
        if (normalized.startsWith("0")) {
            normalized = "+84" + normalized.substring(1);
        }
        return normalized;
    }

    // Statistics inner class
    @lombok.Data
    @lombok.Builder
    public static class CustomerStatistics {
        private Long totalActive;
        private Long totalInactive;
        private Long totalSuspended;
        private Long totalClosed;
        private Long internalClients;
        private Long taxableCustomers;
    }
}