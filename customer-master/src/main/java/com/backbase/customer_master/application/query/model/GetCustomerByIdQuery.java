package com.backbase.customer_master.application.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Query to get customer by ID
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByIdQuery {
    
    @NotBlank(message = "Customer ID is required")
    private String customerId;
}

/**
 * Query to get customer basic info by ID
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomerBasicByIdQuery {
    
    @NotBlank(message = "Customer ID is required")
    private String customerId;
}

/**
 * Query to get all customers with pagination
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetAllCustomersQuery {
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}

/**
 * Query to search customers by name
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SearchCustomersByNameQuery {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}

/**
 * Query to get customers by branch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByBranchQuery {
    
    @NotBlank(message = "Branch ID is required")
    private String branchId;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}

/**
 * Query to get customers by type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByTypeQuery {
    
    @NotBlank(message = "Customer type is required")
    private String customerType;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}

/**
 * Query to get customers by status
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByStatusQuery {
    
    @NotBlank(message = "Status is required")
    private String status;
}

/**
 * Query to find customer by email
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FindCustomerByEmailQuery {
    
    @NotBlank(message = "Email is required")
    private String email;
}

/**
 * Query to find customer by phone number
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FindCustomerByPhoneNumberQuery {
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}

/**
 * Query to find customer by identification number
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FindCustomerByIdentificationNumberQuery {
    
    @NotBlank(message = "Identification number is required")
    private String identificationNumber;
}

/**
 * Query to get customers by date of birth range
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByDateOfBirthRangeQuery {
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
}

/**
 * Query to get PEP customers
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetPepCustomersQuery {
    // No additional fields needed
}

/**
 * Query to get customers in sanctions list
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetSanctionsListCustomersQuery {
    // No additional fields needed
}

/**
 * Query to get customers created in date range
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersCreatedInRangeQuery {
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
}

/**
 * Query to get customers by relationship manager
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByRelationshipManagerQuery {
    
    @NotBlank(message = "Relationship manager ID is required")
    private String relationshipManagerId;
}

/**
 * Query to get customers by KYC status
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByKycStatusQuery {
    
    @NotBlank(message = "KYC status is required")
    private String kycStatus;
}

/**
 * Query to get customers by risk level
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersByRiskLevelQuery {
    
    @NotBlank(message = "Risk level is required")
    private String riskLevel;
}

/**
 * Query to get customers by segment
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class GetCustomersBySegmentQuery {
    
    @NotBlank(message = "Customer segment is required")
    private String customerSegment;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}

/**
 * Query to count customers by status
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CountCustomersByStatusQuery {
    
    @NotBlank(message = "Status is required")
    private String status;
}

/**
 * Query to check if customer exists
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CustomerExistsQuery {
    
    @NotBlank(message = "Customer ID is required")
    private String customerId;
}