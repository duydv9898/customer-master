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

/**
 * Identification Data Transfer Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Customer identification information")
public class IdentificationDTO {

    @Schema(description = "Identification ID")
    private String identificationId;

    @Schema(description = "Customer ID")
    private String customerId;

    @Schema(description = "Identification type ID")
    private String identificationTypeId;

    @Schema(description = "Identification number", example = "123456789")
    private String identificationNumber;

    @Schema(description = "Issue date", example = "2020-01-15")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;

    @Schema(description = "Expiry date", example = "2030-01-15")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @Schema(description = "Issuing authority", example = "Hanoi Police Department")
    private String issuingAuthority;

    @Schema(description = "Issuing country ID")
    private String issuingCountryId;

    @Schema(description = "Issuing province ID")
    private String issuingProvinceId;

    @Schema(description = "Is default identification", example = "true")
    private Boolean isDefault;

    @Schema(description = "Is verified", example = "true")
    private Boolean isVerified;

    @Schema(description = "Verification date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verificationDate;

    @Schema(description = "Verification method", example = "OCR")
    private String verificationMethod;

    @Schema(description = "Verification reference")
    private String verificationReference;

    @Schema(description = "Is active", example = "true")
    private Boolean isActive;

    @Schema(description = "Document image path")
    private String documentImagePath;

    @Schema(description = "OCR extracted data")
    private String ocrExtractedData;

    @Schema(description = "Risk score", example = "75")
    private Integer riskScore;

    @Schema(description = "Blacklist check status", example = "CLEAR")
    private String blacklistCheckStatus;

    @Schema(description = "Blacklist check date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime blacklistCheckDate;

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