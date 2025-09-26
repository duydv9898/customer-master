package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Address Data Transfer Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Customer address information")
public class AddressDTO {

    @Schema(description = "Address ID")
    private String addressId;

    @Schema(description = "Customer ID")
    private String customerId;

    @Schema(description = "Address type", example = "HOME", allowableValues = {"HOME", "WORK", "MAILING", "BILLING"})
    private String addressType;

    @Schema(description = "Address line 1", example = "123 Main Street")
    private String addressLine1;

    @Schema(description = "Address line 2", example = "Apartment 4B")
    private String addressLine2;

    @Schema(description = "Ward ID")
    private String wardId;

    @Schema(description = "District ID")
    private String districtId;

    @Schema(description = "Province ID")
    private String provinceId;

    @Schema(description = "Country ID")
    private String countryId;

    @Schema(description = "Postal code ID")
    private String postalCodeId;

    @Schema(description = "Postal code", example = "10000")
    private String postalCode;

    @Schema(description = "Latitude coordinate", example = "21.0285")
    private Double latitude;

    @Schema(description = "Longitude coordinate", example = "105.8542")
    private Double longitude;

    @Schema(description = "Is default address", example = "true")
    private Boolean isDefault;

    @Schema(description = "Is active address", example = "true")
    private Boolean isActive;

    @Schema(description = "Valid from date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validFrom;

    @Schema(description = "Valid to date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validTo;

    @Schema(description = "Additional notes")
    private String notes;

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
}