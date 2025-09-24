package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Command model for creating a new customer
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {

    @NotBlank(message = "Customer type is required")
    @Size(max = 50, message = "Customer type must not exceed 50 characters")
    private String customerType;

    @NotBlank(message = "Full name is required")
    @Size(max = 200, message = "Full name must not exceed 200 characters")
    private String fullName;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Size(max = 200, message = "Place of birth must not exceed 200 characters")
    private String placeOfBirth;

    @NotBlank(message = "Gender ID is required")
    @Size(max = 36, message = "Gender ID must not exceed 36 characters")
    private String genderId;

    @Size(max = 36, message = "Marital status ID must not exceed 36 characters")
    private String maritalStatusId;

    @Size(max = 36, message = "Nationality ID must not exceed 36 characters")
    private String nationalityId;

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

    @NotBlank(message = "Branch ID is required")
    @Size(max = 36, message = "Branch ID must not exceed 36 characters")
    private String branchId;

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
    @Builder.Default
    private String languagePreference = "vi";

    @Size(max = 50, message = "Communication preference must not exceed 50 characters")
    private String communicationPreference;

    @Builder.Default
    private Boolean isConsentMarketing = false;

    private String notes;

    // Constructor with required fields
    public CreateCustomerCommand(String customerType, String fullName, String firstName, 
                               String lastName, LocalDate dateOfBirth, String genderId, 
                               String branchId) {
        this.customerType = customerType;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.genderId = genderId;
        this.branchId = branchId;
        this.languagePreference = "vi";
        this.isConsentMarketing = false;
    }
}