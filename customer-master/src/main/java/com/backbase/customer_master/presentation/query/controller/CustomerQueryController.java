package com.backbase.customer_master.presentation.query.controller;

import com.backbase.customer_master.application.query.handler.CustomerQueryHandler;
import com.backbase.customer_master.application.query.model.*;
import com.backbase.customer_master.application.query.service.CustomerQueryService;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Customer Query operations (CQRS Pattern)
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Customer Queries", description = "Customer read operations (Get, Search, Find)")
public class CustomerQueryController {

    private final CustomerQueryHandler customerQueryHandler;
    private final CustomerQueryService customerQueryService;

    @Operation(summary = "Get customer by ID", description = "Retrieves a customer by their ID")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        log.info("Retrieving customer: {}", customerId);
//
//        GetCustomerByIdQuery query = GetCustomerByIdQuery.builder()
//                .customerId(customerId)
//                .build();
//
//        CustomerDTO customer = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getCustomerById(customerId));
    }

    @Operation(summary = "Get customer basic info", description = "Retrieves basic customer information without related entities")
    @ApiResponse(responseCode = "200", description = "Customer basic info found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{customerId}/basic")
    public ResponseEntity<CustomerDTO> getCustomerBasicInfo(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        log.info("Retrieving customer basic info: {}", customerId);
        
//        GetCustomerBasicByIdQuery query = GetCustomerBasicByIdQuery.builder()
//                .customerId(customerId)
//                .build();
//
//        CustomerDTO customer = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getCustomerBasicById(customerId));
    }

    @Operation(summary = "Get all customers", description = "Retrieves all customers with pagination")
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Retrieving all customers with pagination");
//
//        GetAllCustomersQuery query = GetAllCustomersQuery.builder()
//                .pageable(pageable)
//                .build();
        
//        Page<CustomerDTO> customers = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getAllCustomers(pageable));
    }

    @Operation(summary = "Search customers by name", description = "Searches customers by full name")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/search")
    public ResponseEntity<Page<CustomerDTO>> searchCustomersByName(
            @Parameter(description = "Name to search for") @RequestParam String name,
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Searching customers by name: {}", name);
//
//        SearchCustomersByNameQuery query = SearchCustomersByNameQuery.builder()
//                .name(name)
//                .pageable(pageable)
//                .build();
//
//        Page<CustomerDTO> customers = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.searchCustomersByName(name, pageable));
    }

    @Operation(summary = "Get customers by branch", description = "Retrieves customers belonging to a specific branch")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<Page<CustomerDTO>> getCustomersByBranch(
            @Parameter(description = "Branch ID") @PathVariable String branchId,
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Retrieving customers by branch: {}", branchId);
        
//        GetCustomersByBranchQuery query = GetCustomersByBranchQuery.builder()
//                .branchId(branchId)
//                .pageable(pageable)
//                .build();
//
//        Page<CustomerDTO> customers = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getCustomersByBranch(branchId, pageable));
    }

    @Operation(summary = "Get customers by type", description = "Retrieves customers of a specific type")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/type/{customerType}")
    public ResponseEntity<Page<CustomerDTO>> getCustomersByType(
            @Parameter(description = "Customer type") @PathVariable String customerType,
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Retrieving customers by type: {}", customerType);
        
//        GetCustomersByTypeQuery query = GetCustomersByTypeQuery.builder()
//                .customerType(customerType)
//                .pageable(pageable)
//                .build();
//
//        Page<CustomerDTO> customers = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getCustomersByType(customerType, pageable));
    }

    @Operation(summary = "Get customers by status", description = "Retrieves customers with a specific status")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByStatus(
            @Parameter(description = "CIF status") @PathVariable String status) {
        log.info("Retrieving customers by status: {}", status);
        
//        GetCustomersByStatusQuery query = GetCustomersByStatusQuery.builder()
//                .status(status)
//                .build();
//
//        List<CustomerDTO> customers = customerQueryHandler.handle(query);
        return ResponseEntity.ok(customerQueryService.getCustomersByStatus(status));
    }

    @Operation(summary = "Find customer by email", description = "Finds a customer by their email address")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> findCustomerByEmail(
            @Parameter(description = "Email address") @PathVariable String email) {
        log.info("Finding customer by email: {}", email);

//        FindCustomerByEmailQuery query = FindCustomerByEmailQuery.builder()
//                .email(email)
//                .build();
//
        Optional<CustomerDTO> customer = customerQueryService.findCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find customer by phone", description = "Finds a customer by their phone number")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<CustomerDTO> findCustomerByPhoneNumber(
            @Parameter(description = "Phone number") @PathVariable String phoneNumber) {
        log.info("Finding customer by phone: {}", phoneNumber);
        
//        FindCustomerByPhoneNumberQuery query = FindCustomerByPhoneNumberQuery.builder()
//                .phoneNumber(phoneNumber)
//                .build();
        
        Optional<CustomerDTO> customer = customerQueryService.findCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find customer by identification", description = "Finds a customer by their identification number")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/identification/{identificationNumber}")
    public ResponseEntity<CustomerDTO> findCustomerByIdentificationNumber(
            @Parameter(description = "Identification number") @PathVariable String identificationNumber) {
        log.info("Finding customer by identification number");
        
        FindCustomerByIdentificationNumberQuery query = FindCustomerByIdentificationNumberQuery.builder()
                .identificationNumber(identificationNumber)
                .build();
        
        Optional<CustomerDTO> customer = customerQueryHandler.handle(query);
        return customer.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get customers by date of birth range", description = "Retrieves customers born within a specific date range")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/date-of-birth")
    public ResponseEntity<List<CustomerDTO>> getCustomersByDateOfBirthRange(
            @Parameter(description = "Start date (yyyy-MM-dd)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date (yyyy-MM-dd)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("Retrieving customers by date of birth range: {} to {}", startDate, endDate);
        
//        GetCustomersByDateOfBirthRangeQuery query = GetCustomersByDateOfBirthRangeQuery.builder()
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
        
        List<CustomerDTO> customers = customerQueryService.getCustomersByDateOfBirthRange(startDate, endDate);
        return ResponseEntity.ok(customers);
    }

//    @Operation(summary = "Get PEP customers", description = "Retrieves all Politically Exposed Person (PEP) customers")
//    @ApiResponse(responseCode = "200", description = "PEP customers found")
//    @GetMapping("/pep")
//    public ResponseEntity<List<CustomerDTO>> getPepCustomers() {
//        log.info("Retrieving PEP customers");
//
//        GetPepCustomersQuery query = GetPepCustomersQuery.builder().build();
//
//        List<CustomerDTO> customers = customerQueryHandler.handle(query);
//        return ResponseEntity.ok(customers);
//    }
//
//    @Operation(summary = "Get sanctions list customers", description = "Retrieves all customers in sanctions list")
//    @ApiResponse(responseCode = "200", description = "Sanctions list customers found")
//    @GetMapping("/sanctions")
//    public ResponseEntity<List<CustomerDTO>> getSanctionsListCustomers() {
//        log.info("Retrieving sanctions list customers");
//
//        GetSanctionsListCustomersQuery query = GetSanctionsListCustomersQuery.builder().build();
//
//        List<CustomerDTO> customers = customerQueryHandler.handle(query);
//        return ResponseEntity.ok(customers);
//    }

    @Operation(summary = "Get customers created in date range", description = "Retrieves customers created within a specific date range")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/created-date")
    public ResponseEntity<List<CustomerDTO>> getCustomersCreatedInRange(
            @Parameter(description = "Start date (yyyy-MM-dd)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date (yyyy-MM-dd)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("Retrieving customers created between: {} and {}", startDate, endDate);
        
//        GetCustomersCreatedInRangeQuery query = GetCustomersCreatedInRangeQuery.builder()
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
        
        List<CustomerDTO> customers = customerQueryService.getCustomersCreatedInRange(startDate, endDate);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customers by relationship manager", description = "Retrieves customers managed by a specific relationship manager")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/relationship-manager/{relationshipManagerId}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByRelationshipManager(
            @Parameter(description = "Relationship manager ID") @PathVariable String relationshipManagerId) {
        log.info("Retrieving customers by relationship manager: {}", relationshipManagerId);
//
//        GetCustomersByRelationshipManagerQuery query = GetCustomersByRelationshipManagerQuery.builder()
//                .relationshipManagerId(relationshipManagerId)
//                .build();
        
        List<CustomerDTO> customers = customerQueryService.getCustomersByRelationshipManager(relationshipManagerId);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customers by KYC status", description = "Retrieves customers with a specific KYC status")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/kyc-status/{kycStatus}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByKycStatus(
            @Parameter(description = "KYC status") @PathVariable String kycStatus) {
        log.info("Retrieving customers by KYC status: {}", kycStatus);
//
//        GetCustomersByKycStatusQuery query = GetCustomersByKycStatusQuery.builder()
//                .kycStatus(kycStatus)
//                .build();
        
        List<CustomerDTO> customers = customerQueryService.getCustomersByKycStatus(kycStatus);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customers by risk level", description = "Retrieves customers with a specific risk level")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/risk-level/{riskLevel}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByRiskLevel(
            @Parameter(description = "Risk level") @PathVariable String riskLevel) {
        log.info("Retrieving customers by risk level: {}", riskLevel);
        
        GetCustomersByRiskLevelQuery query = GetCustomersByRiskLevelQuery.builder()
                .riskLevel(riskLevel)
                .build();
        
        List<CustomerDTO> customers = customerQueryService.getCustomersByRiskLevel(riskLevel);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customers by segment", description = "Retrieves customers belonging to a specific segment")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping("/segment/{customerSegment}")
    public ResponseEntity<Page<CustomerDTO>> getCustomersBySegment(
            @Parameter(description = "Customer segment") @PathVariable String customerSegment,
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Retrieving customers by segment: {}", customerSegment);
        
//        GetCustomersBySegmentQuery query = GetCustomersBySegmentQuery.builder()
//                .customerSegment(customerSegment)
//                .pageable(pageable)
//                .build();
//
        Page<CustomerDTO> customers = customerQueryService.getCustomersBySegment(customerSegment, pageable);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Check customer existence", description = "Checks if a customer exists")
    @ApiResponse(responseCode = "200", description = "Existence check completed")
    @GetMapping("/{customerId}/exists")
    public ResponseEntity<Boolean> customerExists(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        log.debug("Checking if customer exists: {}", customerId);
        
//        CustomerExistsQuery query = CustomerExistsQuery.builder()
//                .customerId(customerId)
//                .build();
        
        Boolean exists = customerQueryService.customerExists(customerId);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Count customers by status", description = "Counts customers with a specific status")
    @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countCustomersByStatus(
            @Parameter(description = "CIF status") @PathVariable String status) {
        log.debug("Counting customers by status: {}", status);
        
//        CountCustomersByStatusQuery query = CountCustomersByStatusQuery.builder()
//                .status(status)
//                .build();
//
        Long count = customerQueryService.countCustomersByStatus(status);
        return ResponseEntity.ok(count);
    }
}