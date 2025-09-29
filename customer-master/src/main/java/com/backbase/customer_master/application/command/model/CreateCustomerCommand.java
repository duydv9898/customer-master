package com.backbase.customer_master.application.command.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {

    @NotBlank(message = "CIF status is required")
    @Size(max = 20, message = "CIF status must not exceed 20 characters")
    private String cifStatus;

    @NotBlank(message = "Full name is required")
    @Size(max = 120, message = "Full name must not exceed 120 characters")
    private String fullName;

    @NotBlank(message = "Gender code is required")
    private String genderCode;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality code is required")
    private String nationalityCode;

    private String maritalStatusCode;

    @NotBlank(message = "Client type code is required")
    private String clientTypeCode;

    private String categoryCode;

    @NotBlank(message = "Primary phone is required")
    @Size(max = 20, message = "Primary phone must not exceed 20 characters")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number format")
    private String primaryPhone;

    @Size(max = 20, message = "Secondary phone must not exceed 20 characters")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number format")
    private String secondaryPhone;

    @Email(message = "Invalid email format")
    @Size(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    private String contactChannelCode;

    private String occupationCode;

    @Size(max = 100, message = "Job title must not exceed 100 characters")
    private String jobTitle;

    private String industryCode;

    private String businessClassCode;

    private String sectorCode;

    @Size(max = 20, message = "Monthly income must not exceed 20 characters")
    private String monthlyIncome;

    @Size(max = 30, message = "Main income source must not exceed 30 characters")
    private String mainIncomeSource;

    @NotBlank(message = "Account usage purpose is required")
    @Size(max = 50, message = "Account usage purpose must not exceed 50 characters")
    private String accountUsagePurpose;

    @Size(max = 1, message = "Internal client must be 1 character")
    @Pattern(regexp = "^[YN]$", message = "Internal client must be Y or N")
    private String internalClient;

    @Size(max = 20, message = "Tax file number must not exceed 20 characters")
    private String taxFileNo;

    @Size(max = 1, message = "Taxable must be 1 character")
    @Pattern(regexp = "^[YN]$", message = "Taxable must be Y or N")
    private String taxable;

    @NotBlank(message = "Registration channel is required")
    @Size(max = 20, message = "Registration channel must not exceed 20 characters")
    private String registrationChannel;

    @NotNull(message = "CIF created date is required")
    private LocalDate cifCreatedDate;

    private String preferredLanguageCode;

    private String segmentCode;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    @Size(max = 30, message = "Customer classification must not exceed 30 characters")
    private String customerClassification;

    @NotBlank(message = "Created by is required")
    @Size(max = 50, message = "Created by must not exceed 50 characters")
    private String createdBy;

    @Size(max = 50, message = "Source app must not exceed 50 characters")
    private String sourceApp;

    @Size(max = 50, message = "Correlation ID must not exceed 50 characters")
    private String correlationId;
}