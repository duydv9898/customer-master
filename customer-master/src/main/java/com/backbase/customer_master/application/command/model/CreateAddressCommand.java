package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Command model for creating an address
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressCommand {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Address type is required")
    @Size(max = 50, message = "Address type must not exceed 50 characters")
    private String addressType;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 200, message = "Address line 1 must not exceed 200 characters")
    private String addressLine1;

    @Size(max = 200, message = "Address line 2 must not exceed 200 characters")
    private String addressLine2;

    @Size(max = 36, message = "Ward ID must not exceed 36 characters")
    private String wardId;

    @NotBlank(message = "District ID is required")
    @Size(max = 36, message = "District ID must not exceed 36 characters")
    private String districtId;

    @NotBlank(message = "Province ID is required")
    @Size(max = 36, message = "Province ID must not exceed 36 characters")
    private String provinceId;

    @NotBlank(message = "Country ID is required")
    @Size(max = 36, message = "Country ID must not exceed 36 characters")
    private String countryId;

    @Size(max = 36, message = "Postal code ID must not exceed 36 characters")
    private String postalCodeId;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;

    private Double latitude;
    private Double longitude;

    @Builder.Default
    private Boolean isDefault = false;

    private String notes;
}