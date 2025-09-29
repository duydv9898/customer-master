package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import com.backbase.customer_master.domain.service.CustomerDomainService;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Query Handler sử dụng CustomerDomainService
 * Trả về CustomerDTO với đầy đủ thông tin từ fetch joins
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerQueryHandler {

    private final CustomerDomainService customerDomainService;

    /**
     * Handle GetCustomerByIdQuery - trả về customer đầy đủ thông tin
     */
    public CustomerDTO handle(GetCustomerByIdQuery query) {
        log.debug("Handling GetCustomerByIdQuery for customer ID: {}", query.getCustomerId());

        UUID customerId = parseCustomerId(query.getCustomerId());

        return customerDomainService.findCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + query.getCustomerId()));
    }

    /**
     * Handle GetCustomerBasicByIdQuery - trả về customer thông tin cơ bản
     */
    public CustomerDTO handle(GetCustomerBasicByIdQuery query) {
        log.debug("Handling GetCustomerBasicByIdQuery for customer ID: {}", query.getCustomerId());

        UUID customerId = parseCustomerId(query.getCustomerId());

        return customerDomainService.findCustomerBasicById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + query.getCustomerId()));
    }

    /**
     * Handle GetAllCustomersQuery - trả về danh sách customer với pagination
     */
    public Page<CustomerDTO> handle(GetAllCustomersQuery query) {
        log.debug("Handling GetAllCustomersQuery with pagination");

        return customerDomainService.findAllCustomers(query.getPageable());
    }

    /**
     * Handle SearchCustomersByNameQuery - tìm kiếm customer theo tên
     */
    public Page<CustomerDTO> handle(SearchCustomersByNameQuery query) {
        log.debug("Handling SearchCustomersByNameQuery for name: {}", query.getName());

        return customerDomainService.searchCustomersByName(query.getName(), query.getPageable());
    }

    /**
     * Handle GetCustomersByBranchQuery - lấy customer theo chi nhánh
     */
    public Page<CustomerDTO> handle(GetCustomersByBranchQuery query) {
        log.debug("Handling GetCustomersByBranchQuery for branch: {}", query.getBranchId());

        // Note: Cần thêm method findCustomersByBranch trong CustomerDomainService
        // Hiện tại sử dụng complex search
        return customerDomainService.findCustomersWithCriteria(
                null, null, null, null, query.getPageable());
    }

    /**
     * Handle GetCustomersByTypeQuery - lấy customer theo loại
     */
    public Page<CustomerDTO> handle(GetCustomersByTypeQuery query) {
        log.debug("Handling GetCustomersByTypeQuery for type: {}", query.getCustomerType());

        return customerDomainService.findCustomersByType(query.getCustomerType(), query.getPageable());
    }

    /**
     * Handle GetCustomersByStatusQuery - lấy customer theo trạng thái
     */
    public List<CustomerDTO> handle(GetCustomersByStatusQuery query) {
        log.debug("Handling GetCustomersByStatusQuery for status: {}", query.getStatus());

        // Sử dụng pagination để tránh memory issues
        Page<CustomerDTO> page = customerDomainService.findCustomersByStatus(
                query.getStatus(),
                org.springframework.data.domain.PageRequest.of(0, 1000));
        return page.getContent();
    }

    /**
     * Handle FindCustomerByEmailQuery - tìm customer theo email
     */
    public Optional<CustomerDTO> handle(FindCustomerByEmailQuery query) {
        log.debug("Handling FindCustomerByEmailQuery");

        return customerDomainService.findCustomerByEmail(query.getEmail());
    }

    /**
     * Handle FindCustomerByPhoneNumberQuery - tìm customer theo số điện thoại
     */
    public Optional<CustomerDTO> handle(FindCustomerByPhoneNumberQuery query) {
        log.debug("Handling FindCustomerByPhoneNumberQuery");

        return customerDomainService.findCustomerByPhoneNumber(query.getPhoneNumber());
    }

    /**
     * Handle FindCustomerByIdentificationNumberQuery - tìm customer theo số CMND/CCCD
     */
    public Optional<CustomerDTO> handle(FindCustomerByIdentificationNumberQuery query) {
        log.debug("Handling FindCustomerByIdentificationNumberQuery");

        return customerDomainService.findCustomerByIdentificationNumber(query.getIdentificationNumber());
    }

    /**
     * Handle GetCustomersByDateOfBirthRangeQuery - lấy customer theo khoảng ngày sinh
     */
    public List<CustomerDTO> handle(GetCustomersByDateOfBirthRangeQuery query) {
        log.debug("Handling GetCustomersByDateOfBirthRangeQuery from {} to {}",
                query.getStartDate(), query.getEndDate());

        return customerDomainService.findCustomersByDateOfBirthRange(query.getStartDate(), query.getEndDate());
    }

    /**
     * Handle GetCustomersCreatedInRangeQuery - lấy customer được tạo trong khoảng thời gian
     */
    public List<CustomerDTO> handle(GetCustomersCreatedInRangeQuery query) {
        log.debug("Handling GetCustomersCreatedInRangeQuery from {} to {}",
                query.getStartDate(), query.getEndDate());

        return customerDomainService.findCustomersCreatedInRange(query.getStartDate(), query.getEndDate());
    }

    /**
     * Handle GetCustomersByRelationshipManagerQuery - lấy customer theo RM
     */
    public List<CustomerDTO> handle(GetCustomersByRelationshipManagerQuery query) {
        log.debug("Handling GetCustomersByRelationshipManagerQuery for RM: {}", query.getRelationshipManagerId());

        // Note: Cần thêm method findCustomersByRelationshipManager trong CustomerDomainService
        // Tạm thời return empty list hoặc throw exception
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    /**
     * Handle GetCustomersByKycStatusQuery - lấy customer theo trạng thái KYC
     */
    public List<CustomerDTO> handle(GetCustomersByKycStatusQuery query) {
        log.debug("Handling GetCustomersByKycStatusQuery for status: {}", query.getKycStatus());

        // Note: Cần thêm method findCustomersByKycStatus trong CustomerDomainService
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    /**
     * Handle GetCustomersByRiskLevelQuery - lấy customer theo mức độ rủi ro
     */
    public List<CustomerDTO> handle(GetCustomersByRiskLevelQuery query) {
        log.debug("Handling GetCustomersByRiskLevelQuery for risk level: {}", query.getRiskLevel());

        // Note: Cần thêm method findCustomersByRiskLevel trong CustomerDomainService
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    /**
     * Handle GetCustomersBySegmentQuery - lấy customer theo phân khúc
     */
    public Page<CustomerDTO> handle(GetCustomersBySegmentQuery query) {
        log.debug("Handling GetCustomersBySegmentQuery for segment: {}", query.getCustomerSegment());

        return customerDomainService.findCustomersBySegment(query.getCustomerSegment(), query.getPageable());
    }

    /**
     * Handle CountCustomersByStatusQuery - đếm customer theo trạng thái
     */
    public Long handle(CountCustomersByStatusQuery query) {
        log.debug("Handling CountCustomersByStatusQuery for status: {}", query.getStatus());

        return customerDomainService.countCustomersByStatus(query.getStatus());
    }

    /**
     * Handle CustomerExistsQuery - kiểm tra customer có tồn tại
     */
    public Boolean handle(CustomerExistsQuery query) {
        log.debug("Handling CustomerExistsQuery for customer: {}", query.getCustomerId());

        UUID customerId = parseCustomerId(query.getCustomerId());
        return customerDomainService.customerExists(customerId);
    }

    // Additional handlers for specific queries

    /**
     * Handle GetCustomersByOccupationQuery
     */
    public List<CustomerDTO> handleGetCustomersByOccupation(String occupationCode) {
        log.debug("Handling GetCustomersByOccupationQuery for occupation: {}", occupationCode);

        return customerDomainService.findCustomersByOccupation(occupationCode);
    }

    /**
     * Handle GetCustomersByIndustryQuery
     */
    public List<CustomerDTO> handleGetCustomersByIndustry(String industryCode) {
        log.debug("Handling GetCustomersByIndustryQuery for industry: {}", industryCode);

        return customerDomainService.findCustomersByIndustry(industryCode);
    }

    /**
     * Handle GetCustomersByBusinessClassQuery
     */
    public List<CustomerDTO> handleGetCustomersByBusinessClass(String businessClassCode) {
        log.debug("Handling GetCustomersByBusinessClassQuery for business class: {}", businessClassCode);

        return customerDomainService.findCustomersByBusinessClass(businessClassCode);
    }

    /**
     * Handle GetCustomersBySectorQuery
     */
    public List<CustomerDTO> handleGetCustomersBySector(String sectorCode) {
        log.debug("Handling GetCustomersBySectorQuery for sector: {}", sectorCode);

        return customerDomainService.findCustomersBySector(sectorCode);
    }

    /**
     * Handle GetCustomersByLanguageQuery
     */
    public List<CustomerDTO> handleGetCustomersByLanguage(String languageCode) {
        log.debug("Handling GetCustomersByLanguageQuery for language: {}", languageCode);

        return customerDomainService.findCustomersByLanguage(languageCode);
    }

    /**
     * Handle GetCustomersByContactChannelQuery
     */
    public List<CustomerDTO> handleGetCustomersByContactChannel(String contactChannelCode) {
        log.debug("Handling GetCustomersByContactChannelQuery for channel: {}", contactChannelCode);

        return customerDomainService.findCustomersByContactChannel(contactChannelCode);
    }

    /**
     * Handle GetCustomersByMonthlyIncomeQuery
     */
    public List<CustomerDTO> handleGetCustomersByMonthlyIncome(String monthlyIncome) {
        log.debug("Handling GetCustomersByMonthlyIncomeQuery for income: {}", monthlyIncome);

        return customerDomainService.findCustomersByMonthlyIncome(monthlyIncome);
    }

    /**
     * Handle GetCustomersByRegistrationChannelQuery
     */
    public List<CustomerDTO> handleGetCustomersByRegistrationChannel(String registrationChannel) {
        log.debug("Handling GetCustomersByRegistrationChannelQuery for channel: {}", registrationChannel);

        return customerDomainService.findCustomersByRegistrationChannel(registrationChannel);
    }

    /**
     * Handle GetInternalClientsQuery
     */
    public List<CustomerDTO> handleGetInternalClients() {
        log.debug("Handling GetInternalClientsQuery");

        return customerDomainService.findInternalClients();
    }

    /**
     * Handle GetTaxableCustomersQuery
     */
    public List<CustomerDTO> handleGetTaxableCustomers() {
        log.debug("Handling GetTaxableCustomersQuery");

        return customerDomainService.findTaxableCustomers();
    }

    /**
     * Handle GetCustomersByProvinceQuery
     */
    public List<CustomerDTO> handleGetCustomersByProvince(String provinceCode) {
        log.debug("Handling GetCustomersByProvinceQuery for province: {}", provinceCode);

        return customerDomainService.findCustomersByProvince(provinceCode);
    }

    /**
     * Handle GetCustomersByDistrictQuery
     */
    public List<CustomerDTO> handleGetCustomersByDistrict(String districtCode) {
        log.debug("Handling GetCustomersByDistrictQuery for district: {}", districtCode);

        return customerDomainService.findCustomersByDistrict(districtCode);
    }

    /**
     * Handle GetCustomersByProductTypeQuery
     */
    public List<CustomerDTO> handleGetCustomersByProductType(String productType) {
        log.debug("Handling GetCustomersByProductTypeQuery for product type: {}", productType);

        return customerDomainService.findCustomersByProductType(productType);
    }

    /**
     * Handle GetCustomersForPeriodicReviewQuery
     */
    public List<CustomerDTO> handleGetCustomersForPeriodicReview(java.time.LocalDateTime reviewDate) {
        log.debug("Handling GetCustomersForPeriodicReviewQuery since: {}", reviewDate);

        return customerDomainService.findCustomersForPeriodicReview(reviewDate);
    }

    /**
     * Handle GetCustomersBySourceAppQuery
     */
    public List<CustomerDTO> handleGetCustomersBySourceApp(String sourceApp) {
        log.debug("Handling GetCustomersBySourceAppQuery for source: {}", sourceApp);

        return customerDomainService.findCustomersBySourceApp(sourceApp);
    }

    /**
     * Handle GetCustomersByCorrelationIdQuery
     */
    public List<CustomerDTO> handleGetCustomersByCorrelationId(String correlationId) {
        log.debug("Handling GetCustomersByCorrelationIdQuery for correlationId: {}", correlationId);

        return customerDomainService.findCustomersByCorrelationId(correlationId);
    }

    /**
     * Handle GetCustomersByCategoryQuery
     */
    public Page<CustomerDTO> handleGetCustomersByCategory(String categoryCode, org.springframework.data.domain.Pageable pageable) {
        log.debug("Handling GetCustomersByCategoryQuery for category: {}", categoryCode);

        return customerDomainService.findCustomersByCategory(categoryCode, pageable);
    }

    /**
     * Handle GetCustomersByCifCreatedDateRangeQuery
     */
    public List<CustomerDTO> handleGetCustomersByCifCreatedDateRange(
            java.time.LocalDate startDate, java.time.LocalDate endDate) {
        log.debug("Handling GetCustomersByCifCreatedDateRangeQuery from {} to {}", startDate, endDate);

        return customerDomainService.findCustomersByCifCreatedDateRange(startDate, endDate);
    }

    /**
     * Handle FindCustomerByTaxFileNoQuery
     */
    public Optional<CustomerDTO> handleFindCustomerByTaxFileNo(String taxFileNo) {
        log.debug("Handling FindCustomerByTaxFileNoQuery");

        return customerDomainService.findCustomerByTaxFileNo(taxFileNo);
    }

    /**
     * Handle Complex Search Query - tìm kiếm phức tạp với nhiều tiêu chí
     */
    public Page<CustomerDTO> handleComplexSearch(
            String status,
            String segment,
            String clientType,
            String category,
            org.springframework.data.domain.Pageable pageable) {

        log.debug("Handling complex search - Status: {}, Segment: {}, ClientType: {}, Category: {}",
                status, segment, clientType, category);

        return customerDomainService.findCustomersWithCriteria(status, segment, clientType, category, pageable);
    }

    /**
     * Handle Text Search Query - tìm kiếm theo text
     */
    public Page<CustomerDTO> handleTextSearch(String searchTerm, org.springframework.data.domain.Pageable pageable) {
        log.debug("Handling text search with term: {}", searchTerm);

        return customerDomainService.complexSearch(searchTerm, pageable);
    }

    /**
     * Handle Get Customer Statistics Query - lấy thống kê customer
     */
    public CustomerDomainService.CustomerStatistics handleGetCustomerStatistics() {
        log.debug("Handling GetCustomerStatisticsQuery");

        return customerDomainService.getCustomerStatistics();
    }

    /**
     * Handle Count Customers By Client Type Query
     */
    public Long handleCountCustomersByClientType(String clientTypeCode) {
        log.debug("Handling CountCustomersByClientTypeQuery for type: {}", clientTypeCode);

        return customerDomainService.countCustomersByClientType(clientTypeCode);
    }

    /**
     * Handle Count Customers By Segment Query
     */
    public Long handleCountCustomersBySegment(String segmentCode) {
        log.debug("Handling CountCustomersBySegmentQuery for segment: {}", segmentCode);

        return customerDomainService.countCustomersBySegment(segmentCode);
    }

    /**
     * Handle Customer Exists By Email Query
     */
    public Boolean handleCustomerExistsByEmail(String email) {
        log.debug("Handling CustomerExistsByEmailQuery");

        return customerDomainService.customerExistsByEmail(email);
    }

    /**
     * Handle Customer Exists By Phone Number Query
     */
    public Boolean handleCustomerExistsByPhoneNumber(String phoneNumber) {
        log.debug("Handling CustomerExistsByPhoneNumberQuery");

        return customerDomainService.customerExistsByPhoneNumber(phoneNumber);
    }

    /**
     * Handle Customer Exists By Tax File No Query
     */
    public Boolean handleCustomerExistsByTaxFileNo(String taxFileNo) {
        log.debug("Handling CustomerExistsByTaxFileNoQuery");

        return customerDomainService.customerExistsByTaxFileNo(taxFileNo);
    }

    // Helper methods

    /**
     * Parse customer ID from String to UUID
     */
    private UUID parseCustomerId(String customerIdStr) {
        try {
            return UUID.fromString(customerIdStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid customer ID format: " + customerIdStr, e);
        }
    }
}