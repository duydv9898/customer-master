package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerCommand {

    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @Size(max = 120, message = "Full name must not exceed 120 characters")
    private String fullName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private String maritalStatusCode;

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

    @Size(max = 50, message = "Account usage purpose must not exceed 50 characters")
    private String accountUsagePurpose;

    @Size(max = 20, message = "Tax file number must not exceed 20 characters")
    private String taxFileNo;

    @Size(max = 1, message = "Taxable must be 1 character")
    @Pattern(regexp = "^[YN]$", message = "Taxable must be Y or N")
    private String taxable;

    private String preferredLanguageCode;

    private String segmentCode;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    @Size(max = 30, message = "Customer classification must not exceed 30 characters")
    private String customerClassification;

    private Integer versionNo;

    @Size(max = 50, message = "Updated by must not exceed 50 characters")
    private String updatedBy;

    @Size(max = 50, message = "Correlation ID must not exceed 50 characters")
    private String correlationId;
}