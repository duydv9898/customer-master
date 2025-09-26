package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime; /**
 * Command to update an existing customer
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerCommand {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @Size(max = 200, message = "Full name must not exceed 200 characters")
    private String fullName;

    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Size(max = 200, message = "Place of birth must not exceed 200 characters")
    private String placeOfBirth;

    private String maritalStatusId;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number format")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid secondary phone number format")
    @Size(max = 20, message = "Secondary phone must not exceed 20 characters")
    private String secondaryPhone;

    @Size(max = 100, message = "Occupation must not exceed 100 characters")
    private String occupation;

    @Size(max = 50, message = "Education level must not exceed 50 characters")
    private String educationLevel;

    @Size(max = 50, message = "Income range must not exceed 50 characters")
    private String incomeRange;

    @Size(max = 20, message = "Risk level must not exceed 20 characters")
    private String riskLevel;

    private Boolean isPep;
    private Boolean isSanctionsList;

    @Size(max = 20, message = "KYC status must not exceed 20 characters")
    private String kycStatus;

    @Size(max = 50, message = "Customer segment must not exceed 50 characters")
    private String customerSegment;

    private String relationshipManagerId;

    @Size(max = 50, message = "Tax ID must not exceed 50 characters")
    private String taxId;

    @Size(max = 100, message = "Source of fund must not exceed 100 characters")
    private String sourceOfFund;

    @Size(max = 100, message = "Referral source must not exceed 100 characters")
    private String referralSource;

    @Size(max = 10, message = "Language preference must not exceed 10 characters")
    private String languagePreference;

    @Size(max = 50, message = "Communication preference must not exceed 50 characters")
    private String communicationPreference;

    private Boolean isConsentMarketing;
    private LocalDateTime consentDate;
    private String notes;
    private Long versionNo;
    private String lastModifiedBy;
}
