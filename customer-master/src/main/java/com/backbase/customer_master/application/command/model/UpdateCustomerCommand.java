package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Command model for updating a customer
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

    @Size(max = 200, message = "Place of birth must not exceed 200 characters")
    private String placeOfBirth;

    @Size(max = 36, message = "Marital status ID must not exceed 36 characters")
    private String maritalStatusId;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Size(max = 20, message = "Secondary phone must not exceed 20 characters")
    private String secondaryPhone;

    @Size(max = 100, message = "Occupation must not exceed 100 characters")
    private String occupation;

    @Size(max = 50, message = "Education level must not exceed 50 characters")
    private String educationLevel;

    @Size(max = 50, message = "Income range must not exceed 50 characters")
    private String incomeRange;

    @Size(max = 50, message = "Customer segment must not exceed 50 characters")
    private String customerSegment;

    @Size(max = 36, message = "Relationship manager ID must not exceed 36 characters")
    private String relationshipManagerId;

    @Size(max = 50, message = "Tax ID must not exceed 50 characters")
    private String taxId;

    @Size(max = 100, message = "Source of fund must not exceed 100 characters")
    private String sourceOfFund;

    @Size(max = 100, message = "Referral source must not exceed 100 characters")
    private String referralSource;

    @Pattern(regexp = "^(vi|en)$", message = "Language preference must be 'vi' or 'en'")
    private String languagePreference;

    @Size(max = 50, message = "Communication preference must not exceed 50 characters")
    private String communicationPreference;

    private Boolean isConsentMarketing;

    private String notes;

    @NotNull(message = "Version number is required for optimistic locking")
    private Long versionNo;
}