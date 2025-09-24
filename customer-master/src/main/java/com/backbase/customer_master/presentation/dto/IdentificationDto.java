package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Identification DTO classes for API layer
 */
public class IdentificationDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String identificationId;
        private String customerId;
        private String identificationTypeId;
        private String identificationNumber;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate issuedDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate;
        
        private String issuingAuthority;
        private String issuingCountryId;
        private String issuingProvinceId;
        private Boolean isDefault;
        private Boolean isVerified;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime verificationDate;
        
        private String verificationMethod;
        private String verificationReference;
        private Boolean isActive;
        private String documentImagePath;
        private Integer riskScore;
        private String blacklistCheckStatus;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime blacklistCheckDate;
        
        private String notes;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastModifiedDate;
        
        private String createdBy;
        private String lastModifiedBy;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private String identificationTypeId;
        private String identificationNumber;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate issuedDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate;
        
        private String issuingAuthority;
        private String issuingCountryId;
        private String issuingProvinceId;
        private Boolean isDefault;
        private String documentImagePath;
        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String identificationNumber;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate issuedDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate;
        
        private String issuingAuthority;
        private String issuingCountryId;
        private String issuingProvinceId;
        private Boolean isDefault;
        private String documentImagePath;
        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationRequest {
        private String verificationMethod;
        private String verificationReference;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private String identificationId;
        private String identificationTypeId;
        private String identificationNumber;
        private LocalDate expiryDate;
        private Boolean isDefault;
        private Boolean isVerified;
        private Boolean isActive;
        private Boolean isExpired;
        private Boolean isExpiringSoon;
    }
}