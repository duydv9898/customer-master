package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Customer Data Transfer Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Customer information")
public class CustomerDTO {

    @Schema(description = "Customer ID (CIF)", example = "CIF1234567890")
    private String customerId;

    @Schema(description = "Customer type", example = "INDIVIDUAL", allowableValues = {"INDIVIDUAL", "CORPORATE", "SME"})
    private String customerType;

    @Schema(description = "Full name of the customer", example = "Nguyen Van A")
    private String fullName;

    @Schema(description = "First name", example = "Van A")
    private String firstName;

    @Schema(description = "Middle name", example = "Van")
    private String middleName;

    @Schema(description = "Last name", example = "Nguyen")
    private String lastName;

    @Schema(description = "Date of birth", example = "1990-01-15")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Schema(description = "Place of birth", example = "Hanoi, Vietnam")
    private String placeOfBirth;

    @Schema(description = "Gender ID")
    private String genderId;

    @Schema(description = "Marital status ID")
    private String maritalStatusId;

    @Schema(description = "Nationality ID")
    private String nationalityId;

    @Schema(description = "Email address", example = "nguyenvana@example.com")
    private String email;

    @Schema(description = "Primary phone number", example = "+84901234567")
    private String phoneNumber;

    @Schema(description = "Secondary phone number", example = "+84987654321")
    private String secondaryPhone;

    @Schema(description = "Occupation", example = "Software Engineer")
    private String occupation;

    @Schema(description = "Education level", example = "Bachelor's Degree")
    private String educationLevel;

    @Schema(description = "Income range", example = "20M-50M VND")
    private String incomeRange;

    @Schema(description = "CIF status", example = "ACTIVE", allowableValues = {"ACTIVE", "INACTIVE", "SUSPENDED", "CLOSED"})
    private String cifStatus;

    @Schema(description = "Risk level", example = "LOW", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private String riskLevel;

    @Schema(description = "Is Politically Exposed Person", example = "false")
    private Boolean isPep;

    @Schema(description = "Is in sanctions list", example = "false")
    private Boolean isSanctionsList;

    @Schema(description = "KYC status", example = "COMPLETED", allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED", "REJECTED"})
    private String kycStatus;

    @Schema(description = "Branch ID where customer was onboarded")
    private String branchId;

    @Schema(description = "Customer segment", example = "PREMIUM")
    private String customerSegment;

    @Schema(description = "Relationship manager ID")
    private String relationshipManagerId;

    @Schema(description = "Tax identification number")
    private String taxId;

    @Schema(description = "Source of funds", example = "Salary")
    private String sourceOfFund;

    @Schema(description = "Referral source", example = "Online")
    private String referralSource;

    @Schema(description = "Language preference", example = "vi", allowableValues = {"vi", "en"})
    private String languagePreference;

    @Schema(description = "Communication preference", example = "EMAIL")
    private String communicationPreference;

    @Schema(description = "Marketing consent", example = "true")
    private Boolean isConsentMarketing;

    @Schema(description = "Consent date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consentDate;

    @Schema(description = "Last contact date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastContactDate;

    @Schema(description = "Additional notes")
    private String notes;

    @Schema(description = "Version number for optimistic locking")
    private Long versionNo;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Schema(description = "Last modified date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Schema(description = "Created by user")
    private String createdBy;

    @Schema(description = "Last modified by user")
    private String lastModifiedBy;

    // Related entities
    @Schema(description = "Customer addresses")
    private List<AddressDTO> addressDTOs;

    @Schema(description = "Customer identifications")
    private List<IdentificationDTO> identificationDTOs;

    @Schema(description = "Customer products")
    private List<CustomerProductDTO> productDTOs;

    @Schema(description = "Customer relationships")
    private List<CustomerRelationshipDTO> relationshipDTOs;
}