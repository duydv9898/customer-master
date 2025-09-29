package com.backbase.customer_master.application.query.service;

import com.backbase.customer_master.application.query.handler.CustomerQueryHandler;
import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.domain.service.CustomerDomainService;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // Basic queries

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
     * Find customer by tax file number
     */
    public Optional<CustomerDTO> findCustomerByTaxFileNo(String taxFileNo) {
        log.debug("Processing find customer by tax file number query");

        return customerQueryHandler.handleFindCustomerByTaxFileNo(taxFileNo);
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
     * Get customers by CIF created date range
     */
    public List<CustomerDTO> getCustomersByCifCreatedDateRange(LocalDate startDate, LocalDate endDate) {
        log.debug("Processing get customers by CIF created date range: {} to {}", startDate, endDate);

        return customerQueryHandler.handleGetCustomersByCifCreatedDateRange(startDate, endDate);
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
     * Get customers by category
     */
    public Page<CustomerDTO> getCustomersByCategory(String categoryCode, Pageable pageable) {
        log.debug("Processing get customers by category query for: {}", categoryCode);

        return customerQueryHandler.handleGetCustomersByCategory(categoryCode, pageable);
    }

    /**
     * Get customers by occupation
     */
    public List<CustomerDTO> getCustomersByOccupation(String occupationCode) {
        log.debug("Processing get customers by occupation query for: {}", occupationCode);

        return customerQueryHandler.handleGetCustomersByOccupation(occupationCode);
    }

    /**
     * Get customers by industry
     */
    public List<CustomerDTO> getCustomersByIndustry(String industryCode) {
        log.debug("Processing get customers by industry query for: {}", industryCode);

        return customerQueryHandler.handleGetCustomersByIndustry(industryCode);
    }

    /**
     * Get customers by business classification
     */
    public List<CustomerDTO> getCustomersByBusinessClass(String businessClassCode) {
        log.debug("Processing get customers by business class query for: {}", businessClassCode);

        return customerQueryHandler.handleGetCustomersByBusinessClass(businessClassCode);
    }

    /**
     * Get customers by economic sector
     */
    public List<CustomerDTO> getCustomersBySector(String sectorCode) {
        log.debug("Processing get customers by sector query for: {}", sectorCode);

        return customerQueryHandler.handleGetCustomersBySector(sectorCode);
    }

    /**
     * Get customers by language preference
     */
    public List<CustomerDTO> getCustomersByLanguage(String languageCode) {
        log.debug("Processing get customers by language query for: {}", languageCode);

        return customerQueryHandler.handleGetCustomersByLanguage(languageCode);
    }

    /**
     * Get customers by contact channel
     */
    public List<CustomerDTO> getCustomersByContactChannel(String contactChannelCode) {
        log.debug("Processing get customers by contact channel query for: {}", contactChannelCode);

        return customerQueryHandler.handleGetCustomersByContactChannel(contactChannelCode);
    }

    /**
     * Get customers by monthly income
     */
    public List<CustomerDTO> getCustomersByMonthlyIncome(String monthlyIncome) {
        log.debug("Processing get customers by monthly income query for: {}", monthlyIncome);

        return customerQueryHandler.handleGetCustomersByMonthlyIncome(monthlyIncome);
    }

    /**
     * Get customers by registration channel
     */
    public List<CustomerDTO> getCustomersByRegistrationChannel(String registrationChannel) {
        log.debug("Processing get customers by registration channel query for: {}", registrationChannel);

        return customerQueryHandler.handleGetCustomersByRegistrationChannel(registrationChannel);
    }

    /**
     * Get internal clients
     */
    public List<CustomerDTO> getInternalClients() {
        log.debug("Processing get internal clients query");

        return customerQueryHandler.handleGetInternalClients();
    }

    /**
     * Get taxable customers
     */
    public List<CustomerDTO> getTaxableCustomers() {
        log.debug("Processing get taxable customers query");

        return customerQueryHandler.handleGetTaxableCustomers();
    }

    /**
     * Get customers by province
     */
    public List<CustomerDTO> getCustomersByProvince(String provinceCode) {
        log.debug("Processing get customers by province query for: {}", provinceCode);

        return customerQueryHandler.handleGetCustomersByProvince(provinceCode);
    }

    /**
     * Get customers by district
     */
    public List<CustomerDTO> getCustomersByDistrict(String districtCode) {
        log.debug("Processing get customers by district query for: {}", districtCode);

        return customerQueryHandler.handleGetCustomersByDistrict(districtCode);
    }

    /**
     * Get customers by product type
     */
    public List<CustomerDTO> getCustomersByProductType(String productType) {
        log.debug("Processing get customers by product type query for: {}", productType);

        return customerQueryHandler.handleGetCustomersByProductType(productType);
    }

    /**
     * Get customers by source application
     */
    public List<CustomerDTO> getCustomersBySourceApp(String sourceApp) {
        log.debug("Processing get customers by source app query for: {}", sourceApp);

        return customerQueryHandler.handleGetCustomersBySourceApp(sourceApp);
    }

    /**
     * Get customers by correlation ID
     */
    public List<CustomerDTO> getCustomersByCorrelationId(String correlationId) {
        log.debug("Processing get customers by correlation ID query for: {}", correlationId);

        return customerQueryHandler.handleGetCustomersByCorrelationId(correlationId);
    }

    /**
     * Get customers for periodic review
     */
    public List<CustomerDTO> getCustomersForPeriodicReview(LocalDateTime reviewDate) {
        log.debug("Processing get customers for periodic review query");

        return customerQueryHandler.handleGetCustomersForPeriodicReview(reviewDate);
    }

    /**
     * Complex search with multiple criteria
     */
    public Page<CustomerDTO> complexSearch(String status, String segment, String clientType, String category, Pageable pageable) {
        log.debug("Processing complex search query");

        return customerQueryHandler.handleComplexSearch(status, segment, clientType, category, pageable);
    }

    /**
     * Text search
     */
    public Page<CustomerDTO> textSearch(String searchTerm, Pageable pageable) {
        log.debug("Processing text search query with term: {}", searchTerm);

        return customerQueryHandler.handleTextSearch(searchTerm, pageable);
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
     * Count customers by client type
     */
    public Long countCustomersByClientType(String clientTypeCode) {
        log.debug("Processing count customers by client type query for: {}", clientTypeCode);

        return customerQueryHandler.handleCountCustomersByClientType(clientTypeCode);
    }

    /**
     * Count customers by segment
     */
    public Long countCustomersBySegment(String segmentCode) {
        log.debug("Processing count customers by segment query for: {}", segmentCode);

        return customerQueryHandler.handleCountCustomersBySegment(segmentCode);
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

    /**
     * Check if customer exists by email
     */
    public Boolean customerExistsByEmail(String email) {
        log.debug("Processing customer exists by email query");

        return customerQueryHandler.handleCustomerExistsByEmail(email);
    }

    /**
     * Check if customer exists by phone number
     */
    public Boolean customerExistsByPhoneNumber(String phoneNumber) {
        log.debug("Processing customer exists by phone number query");

        return customerQueryHandler.handleCustomerExistsByPhoneNumber(phoneNumber);
    }

    /**
     * Check if customer exists by tax file number
     */
    public Boolean customerExistsByTaxFileNo(String taxFileNo) {
        log.debug("Processing customer exists by tax file number query");

        return customerQueryHandler.handleCustomerExistsByTaxFileNo(taxFileNo);
    }

    /**
     * Get customer statistics
     */
    public CustomerDomainService.CustomerStatistics getCustomerStatistics() {
        log.debug("Processing get customer statistics query");

        return customerQueryHandler.handleGetCustomerStatistics();
    }
}