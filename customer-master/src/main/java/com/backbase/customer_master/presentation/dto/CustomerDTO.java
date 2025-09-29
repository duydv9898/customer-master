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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Customer information")
public class CustomerDTO {

    @Schema(description = "Customer ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID customerId;

    @Schema(description = "CIF status", example = "ACTIVE")
    private String cifStatus;

    @Schema(description = "Full name", example = "Nguyen Van A")
    private String fullName;

    @Schema(description = "Gender code")
    private String genderCode;

    @Schema(description = "Gender name")
    private String genderName;

    @Schema(description = "Date of birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Schema(description = "Nationality code")
    private String nationalityCode;

    @Schema(description = "Nationality name")
    private String nationalityName;

    @Schema(description = "Marital status code")
    private String maritalStatusCode;

    @Schema(description = "Marital status name")
    private String maritalStatusName;

    @Schema(description = "Client type code")
    private String clientTypeCode;

    @Schema(description = "Client type name")
    private String clientTypeName;

    @Schema(description = "Category code")
    private String categoryCode;

    @Schema(description = "Category name")
    private String categoryName;

    @Schema(description = "Primary phone", example = "+84901234567")
    private String primaryPhone;

    @Schema(description = "Secondary phone")
    private String secondaryPhone;

    @Schema(description = "Email", example = "customer@example.com")
    private String email;

    @Schema(description = "Preferred contact channel code")
    private String contactChannelCode;

    @Schema(description = "Preferred contact channel name")
    private String contactChannelName;

    @Schema(description = "Occupation code")
    private String occupationCode;

    @Schema(description = "Occupation name")
    private String occupationName;

    @Schema(description = "Job title")
    private String jobTitle;

    @Schema(description = "Industry code")
    private String industryCode;

    @Schema(description = "Industry name")
    private String industryName;

    @Schema(description = "Business classification code")
    private String businessClassCode;

    @Schema(description = "Business classification name")
    private String businessClassName;

    @Schema(description = "Economic sector code")
    private String sectorCode;

    @Schema(description = "Economic sector name")
    private String sectorName;

    @Schema(description = "Monthly income")
    private String monthlyIncome;

    @Schema(description = "Main income source")
    private String mainIncomeSource;

    @Schema(description = "Account usage purpose")
    private String accountUsagePurpose;

    @Schema(description = "Internal client flag", example = "Y")
    private String internalClient;

    @Schema(description = "Tax file number")
    private String taxFileNo;

    @Schema(description = "Taxable flag", example = "Y")
    private String taxable;

    @Schema(description = "Registration channel")
    private String registrationChannel;

    @Schema(description = "CIF created date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cifCreatedDate;

    @Schema(description = "Preferred language code")
    private String preferredLanguageCode;

    @Schema(description = "Preferred language name")
    private String preferredLanguageName;

    @Schema(description = "Customer segment code")
    private String segmentCode;

    @Schema(description = "Customer segment name")
    private String segmentName;

    @Schema(description = "Notes")
    private String notes;

    @Schema(description = "Customer classification")
    private String customerClassification;

    @Schema(description = "Version number for optimistic locking")
    private Integer versionNo;

    @Schema(description = "Created at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Created by")
    private String createdBy;

    @Schema(description = "Updated at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(description = "Updated by")
    private String updatedBy;

    @Schema(description = "Source application")
    private String sourceApp;

    @Schema(description = "Correlation ID")
    private String correlationId;

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