package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.service.CustomerDomainService;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

/**
 * Query Handler sử dụng CustomerDomainService với JPA Specifications
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

        return customerDomainService.findCustomerById(query.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + query.getCustomerId()));
    }

    /**
     * Handle GetCustomerBasicByIdQuery - trả về customer thông tin cơ bản
     */
    public CustomerDTO handle(GetCustomerBasicByIdQuery query) {
        log.debug("Handling GetCustomerBasicByIdQuery for customer ID: {}", query.getCustomerId());

        return customerDomainService.findCustomerBasicById(query.getCustomerId())
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

        return customerDomainService.findCustomersByBranch(query.getBranchId(), query.getPageable());
    }

    /**
     * Handle GetCustomersByTypeQuery - lấy customer theo loại
     */
    public Page<CustomerDTO> handle(GetCustomersByTypeQuery query) {
        log.debug("Handling GetCustomersByTypeQuery for type: {}", query.getCustomerType());

//        Customer.CustomerType customerType;
//        try {
//            customerType = Customer.CustomerType.valueOf(query.getCustomerType().toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid customer type: " + query.getCustomerType());
//        }

        return customerDomainService.findCustomersByType(query.getCustomerType(), query.getPageable());
    }

    /**
     * Handle GetCustomersByStatusQuery - lấy customer theo trạng thái
     */
    public List<CustomerDTO> handle(GetCustomersByStatusQuery query) {
        log.debug("Handling GetCustomersByStatusQuery for status: {}", query.getStatus());

        // Sử dụng pagination để tránh memory issues
        Page<CustomerDTO> page = customerDomainService.findCustomersByStatus(query.getStatus(),
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

        return customerDomainService.findCustomersByRelationshipManager(query.getRelationshipManagerId());
    }

    /**
     * Handle GetCustomersByKycStatusQuery - lấy customer theo trạng thái KYC
     */
    public List<CustomerDTO> handle(GetCustomersByKycStatusQuery query) {
        log.debug("Handling GetCustomersByKycStatusQuery for status: {}", query.getKycStatus());

//        Customer.KycStatus kycStatus;
//        try {
//            kycStatus = Customer.KycStatus.valueOf(query.getKycStatus().toUpperCase().replace(" ", "_"));
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid KYC status: " + query.getKycStatus());
//        }

        return customerDomainService.findCustomersByKycStatus(query.getKycStatus());
    }

    /**
     * Handle GetCustomersByRiskLevelQuery - lấy customer theo mức độ rủi ro
     */
    public List<CustomerDTO> handle(GetCustomersByRiskLevelQuery query) {
        log.debug("Handling GetCustomersByRiskLevelQuery for risk level: {}", query.getRiskLevel());

//        Customer.RiskLevel riskLevel;
//        try {
//            riskLevel = Customer.RiskLevel.valueOf(query.getRiskLevel().toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid risk level: " + query.getRiskLevel());
//        }

        return customerDomainService.findCustomersByRiskLevel(query.getRiskLevel());
    }

    /**
     * Handle GetCustomersBySegmentQuery - lấy customer theo phân khúc
     */
    public Page<CustomerDTO> handle(GetCustomersBySegmentQuery query) {
        log.debug("Handling GetCustomersBySegmentQuery for segment: {}", query.getCustomerSegment());

//        Customer.CustomerSegment segment;
//        try {
//            segment = Customer.CustomerSegment.valueOf(query.getCustomerSegment().toUpperCase().replace(" ", "_"));
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid customer segment: " + query.getCustomerSegment());
//        }

        return customerDomainService.findCustomersBySegment(query.getCustomerSegment(), query.getPageable());
    }

    /**
     * Handle CountCustomersByStatusQuery - đếm customer theo trạng thái
     */
    public Long handle(CountCustomersByStatusQuery query) {
        log.debug("Handling CountCustomersByStatusQuery for status: {}", query.getStatus());

//        Customer.CifStatus status;
//        try {
//            status = Customer.CifStatus.valueOf(query.getStatus().toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid CIF status: " + query.getStatus());
//        }

        return customerDomainService.countCustomersByStatus(query.getStatus());
    }

    /**
     * Handle CustomerExistsQuery - kiểm tra customer có tồn tại
     */
    public Boolean handle(CustomerExistsQuery query) {
        log.debug("Handling CustomerExistsQuery for customer: {}", query.getCustomerId());

        return customerDomainService.customerExists(query.getCustomerId());
    }

    // Additional handlers cho các queries đặc biệt

    /**
     * Handle GetPepCustomersQuery - lấy danh sách PEP customers
     */
    public List<CustomerDTO> handleGetPepCustomers() {
        log.debug("Handling GetPepCustomersQuery");

        return customerDomainService.findPepCustomers();
    }

    /**
     * Handle GetSanctionsListCustomersQuery - lấy danh sách sanctions customers
     */
    public List<CustomerDTO> handleGetSanctionsListCustomers() {
        log.debug("Handling GetSanctionsListCustomersQuery");

        return customerDomainService.findSanctionsListCustomers();
    }

    /**
     * Handle GetHighRiskCustomersQuery - lấy danh sách high-risk customers
     */
    public List<CustomerDTO> handleGetHighRiskCustomers() {
        log.debug("Handling GetHighRiskCustomersQuery");

        return customerDomainService.findHighRiskCustomers();
    }

    /**
     * Handle GetCustomersWithExpiredIdQuery - lấy customer có giấy tờ hết hạn
     */
    public List<CustomerDTO> handleGetCustomersWithExpiredId() {
        log.debug("Handling GetCustomersWithExpiredIdQuery");

        return customerDomainService.findCustomersWithExpiredId();
    }

    /**
     * Handle GetCustomersWithIdExpiringSoonQuery - lấy customer có giấy tờ sắp hết hạn
     */
    public List<CustomerDTO> handleGetCustomersWithIdExpiringSoon(int daysAhead) {
        log.debug("Handling GetCustomersWithIdExpiringSoonQuery for {} days ahead", daysAhead);

        return customerDomainService.findCustomersWithIdExpiringSoon(daysAhead);
    }

    /**
     * Handle Complex Search Query - tìm kiếm phức tạp với nhiều tiêu chí
     */
    public Page<CustomerDTO> handleComplexSearch(
            String status, String segment, String riskLevel, String branchId,
            org.springframework.data.domain.Pageable pageable) {

        log.debug("Handling complex search - Status: {}, Segment: {}, Risk: {}, Branch: {}",
                status, segment, riskLevel, branchId);

//        Customer.CifStatus cifStatus = null;
//        Customer.CustomerSegment customerSegment = null;
//        Customer.RiskLevel customerRiskLevel = null;
//
//        try {
//            if (status != null && !status.trim().isEmpty()) {
//                cifStatus = Customer.CifStatus.valueOf(status.toUpperCase());
//            }
//            if (segment != null && !segment.trim().isEmpty()) {
//                customerSegment = Customer.CustomerSegment.valueOf(segment.toUpperCase().replace(" ", "_"));
//            }
//            if (riskLevel != null && !riskLevel.trim().isEmpty()) {
//                customerRiskLevel = Customer.RiskLevel.valueOf(riskLevel.toUpperCase());
//            }
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Invalid search criteria: " + ex.getMessage());
//        }

        return customerDomainService.findCustomersWithCriteria(status, segment, segment, branchId, pageable);
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
     * Handle Get Customers By Province Query - lấy customer theo tỉnh
     */
    public List<CustomerDTO> handleGetCustomersByProvince(String provinceId) {
        log.debug("Handling GetCustomersByProvinceQuery for province: {}", provinceId);

        return customerDomainService.findCustomersByProvince(provinceId);
    }

    /**
     * Handle Get Customers By District Query - lấy customer theo quận/huyện
     */
    public List<CustomerDTO> handleGetCustomersByDistrict(String districtId) {
        log.debug("Handling GetCustomersByDistrictQuery for district: {}", districtId);

        return customerDomainService.findCustomersByDistrict(districtId);
    }

    /**
     * Handle Get Customers By Product Type Query - lấy customer theo loại sản phẩm
     */
    public List<CustomerDTO> handleGetCustomersByProductType(String productType) {
        log.debug("Handling GetCustomersByProductTypeQuery for product type: {}", productType);

        return customerDomainService.findCustomersByProductType(productType);
    }

    /**
     * Handle Get Customers For Periodic Review Query - lấy customer cần review định kỳ
     */
    public List<CustomerDTO> handleGetCustomersForPeriodicReview(java.time.LocalDateTime reviewDate) {
        log.debug("Handling GetCustomersForPeriodicReviewQuery since: {}", reviewDate);

        return customerDomainService.findCustomersForPeriodicReview(reviewDate);
    }
}