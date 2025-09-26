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
import java.util.stream.Collectors;

/**
 * Customer Domain Service sử dụng JPA Specifications
 * Trả về CustomerDTO với đầy đủ thông tin từ fetch joins
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerDomainService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Find customer by ID với tất cả dữ liệu liên quan
     */
    public Optional<CustomerDTO> findCustomerById(String customerId) {
        log.debug("Finding customer by ID: {}", customerId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasCustomerId(customerId))
                .and(CustomerSpecifications.excludeClosed());
        
        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by ID với dữ liệu cơ bản
     */
    public Optional<CustomerDTO> findCustomerBasicById(String customerId) {
        log.debug("Finding customer basic info by ID: {}", customerId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasCustomerId(customerId))
                .and(CustomerSpecifications.excludeClosed());
        
        return customerRepository.findOne(spec)
                .map(customerMapper::toBasicDTO);
    }

    /**
     * Find customer by email với tất cả dữ liệu
     */
    public Optional<CustomerDTO> findCustomerByEmail(String email) {
        log.debug("Finding customer by email");
        
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasEmailHash(email.toLowerCase()))
                .and(CustomerSpecifications.excludeClosed());
        
        return customerRepository.findOne(spec)
                .map(customerMapper::toDTO);
    }

    /**
     * Find customer by phone number với tất cả dữ liệu
     */
    public Optional<CustomerDTO> findCustomerByPhoneNumber(String phoneNumber) {
        log.debug("Finding customer by phone number");
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return Optional.empty();
        }
        
        String normalizedPhone = normalizePhoneNumber(phoneNumber);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasPhoneHash(normalizedPhone))
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
     * Get all customers với pagination (summary view)
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
     * Find customers by status
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
     * Find customers by branch
     */
    public Page<CustomerDTO> findCustomersByBranch(String branchId, Pageable pageable) {
        log.debug("Finding customers by branch: {}", branchId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasBranchId(branchId))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Find customers by type
     */
    public Page<CustomerDTO> findCustomersByType(String customerType, Pageable pageable) {
        log.debug("Finding customers by type: {}", customerType);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasCustomerType(customerType))
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
    public Page<CustomerDTO> findCustomersBySegment(String customerSegment, Pageable pageable) {
        log.debug("Finding customers by segment: {}", customerSegment);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasCustomerSegment(customerSegment))
                .and(CustomerSpecifications.isActive());
        
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
     * Find customers by relationship manager
     */
    public List<CustomerDTO> findCustomersByRelationshipManager(String relationshipManagerId) {
        log.debug("Finding customers by relationship manager: {}", relationshipManagerId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.hasRelationshipManagerId(relationshipManagerId))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find PEP customers với compliance data
     */
    public List<CustomerDTO> findPepCustomers() {
        log.debug("Finding PEP customers");
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.isPep(true))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for compliance review
                .collect(Collectors.toList());
    }

    /**
     * Find customers in sanctions list
     */
    public List<CustomerDTO> findSanctionsListCustomers() {
        log.debug("Finding sanctions list customers");
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.isSanctionsList(true))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for compliance review
                .collect(Collectors.toList());
    }

    /**
     * Find customers by KYC status
     */
    public List<CustomerDTO> findCustomersByKycStatus(String kycStatus) {
        log.debug("Finding customers by KYC status: {}", kycStatus);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasKycStatus(kycStatus))
                .and(CustomerSpecifications.excludeClosed());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for KYC review
                .collect(Collectors.toList());
    }

    /**
     * Find customers by risk level
     */
    public List<CustomerDTO> findCustomersByRiskLevel(String riskLevel) {
        log.debug("Finding customers by risk level: {}", riskLevel);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasRiskLevel(riskLevel))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for risk review
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
     * Find high-risk customers
     */
    public List<CustomerDTO> findHighRiskCustomers() {
        log.debug("Finding high-risk customers");
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.isHighRisk())
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for risk management
                .collect(Collectors.toList());
    }

    /**
     * Find customers with expired identifications
     */
    public List<CustomerDTO> findCustomersWithExpiredId() {
        log.debug("Finding customers with expired identifications");
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasExpiredIdentification())
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for compliance action
                .collect(Collectors.toList());
    }

    /**
     * Find customers with identification expiring soon
     */
    public List<CustomerDTO> findCustomersWithIdExpiringSoon(int daysAhead) {
        log.debug("Finding customers with ID expiring in {} days", daysAhead);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.hasIdentificationExpiringSoon(daysAhead))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for proactive customer contact
                .collect(Collectors.toList());
    }

    /**
     * Find customers requiring KYC renewal
     */
    public List<CustomerDTO> findCustomersRequiringKycRenewal(LocalDateTime cutoffDate) {
        log.debug("Finding customers requiring KYC renewal since: {}", cutoffDate);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withAllRelatedData())
                .and(CustomerSpecifications.requiresKycRenewal(cutoffDate))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for KYC renewal process
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
                    CustomerSpecifications.isHighRisk(),
                    CustomerSpecifications.isPep(true)
                ))
                .and(Specification.anyOf(
                    Specification.where((root, query, cb) -> cb.isNull(root.get("lastModifiedDate"))),
                    CustomerSpecifications.modifiedAfter(reviewDate)
                ));
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDTO) // Full DTO for review process
                .collect(Collectors.toList());
    }

    /**
     * Complex search with multiple criteria
     */
    public Page<CustomerDTO> findCustomersWithCriteria(
            String status,
            String segment,
            String riskLevel,
            String branchId,
            Pageable pageable) {
        
        log.debug("Finding customers with criteria - Status: {}, Segment: {}, Risk: {}, Branch: {}", 
                 status, segment, riskLevel, branchId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withSummaryData())
                .and(CustomerSpecifications.excludeClosed());
        
        // Add optional criteria
        if (status != null) {
            spec = spec.and(CustomerSpecifications.hasCifStatus(status));
        }
        if (segment != null) {
            spec = spec.and(CustomerSpecifications.hasCustomerSegment(segment));
        }
        if (riskLevel != null) {
            spec = spec.and(CustomerSpecifications.hasRiskLevel(riskLevel));
        }
        if (branchId != null) {
            spec = spec.and(CustomerSpecifications.hasBranchId(branchId));
        }
        
        return customerRepository.findAll(spec, pageable)
                .map(customerMapper::toSummaryDTO);
    }

    /**
     * Advanced search with text search
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

    /**
     * Find customers by province (address-based search)
     */
    public List<CustomerDTO> findCustomersByProvince(String provinceId) {
        log.debug("Finding customers by province: {}", provinceId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasAddressInProvince(provinceId))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toBasicDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find customers by district (address-based search)
     */
    public List<CustomerDTO> findCustomersByDistrict(String districtId) {
        log.debug("Finding customers by district: {}", districtId);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.withBasicRelatedData())
                .and(CustomerSpecifications.hasAddressInDistrict(districtId))
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
                .map(customerMapper::toDTO) // Full DTO to show products
                .collect(Collectors.toList());
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
     * Count customers by risk level
     */
    public Long countCustomersByRiskLevel(String riskLevel) {
        log.debug("Counting customers by risk level: {}", riskLevel);
        
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.hasRiskLevel(riskLevel))
                .and(CustomerSpecifications.isActive());
        
        return customerRepository.count(spec);
    }

    /**
     * Check if customer exists by ID
     */
    public Boolean customerExists(String customerId) {
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
        
//        String emailHash = dataProtectionService.hashSensitiveData(email.trim().toLowerCase());
        Specification<Customer> spec = CustomerSpecifications.hasEmailHash(email.toLowerCase());
        
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
//        String phoneHash = dataProtectionService.hashSensitiveData(normalizedPhone);
        Specification<Customer> spec = CustomerSpecifications.hasPhoneHash(normalizedPhone);
        
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
        
        long highRisk = customerRepository.count(
            Specification.where(CustomerSpecifications.isHighRisk())
                        .and(CustomerSpecifications.isActive())
        );
        
        long pepCustomers = customerRepository.count(
            Specification.where(CustomerSpecifications.isPep(true))
                        .and(CustomerSpecifications.isActive())
        );
        
        long sanctionsCustomers = customerRepository.count(
            Specification.where(CustomerSpecifications.isSanctionsList(true))
                        .and(CustomerSpecifications.isActive())
        );

        return CustomerStatistics.builder()
                .totalActive(totalActive)
                .totalInactive(totalInactive)
                .totalSuspended(totalSuspended)
                .totalClosed(totalClosed)
                .highRisk(highRisk)
                .pepCustomers(pepCustomers)
                .sanctionsCustomers(sanctionsCustomers)
                .build();
    }

    // Private helper methods
    
    private String normalizePhoneNumber(String phone) {
        if (phone == null) return null;
        String normalized = phone.replaceAll("[^0-9+]", "");
        if (normalized.startsWith("0")) {
            normalized = "+84" + normalized.substring(1);
        }
        return normalized;
    }

    // Inner class for statistics
    @lombok.Data
    @lombok.Builder
    public static class CustomerStatistics {
        private Long totalActive;
        private Long totalInactive;
        private Long totalSuspended;
        private Long totalClosed;
        private Long highRisk;
        private Long pepCustomers;
        private Long sanctionsCustomers;
    }
}