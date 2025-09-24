package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Address DTO classes for API layer
 */
public class AddressDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String addressId;
        private String customerId;
        private String addressType;
        private String addressLine1;
        private String addressLine2;
        private String wardId;
        private String districtId;
        private String provinceId;
        private String countryId;
        private String postalCodeId;
        private String postalCode;
        private Double latitude;
        private Double longitude;
        private Boolean isDefault;
        private Boolean isActive;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validFrom;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validTo;
        
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
        private String addressType;
        private String addressLine1;
        private String addressLine2;
        private String wardId;
        private String districtId;
        private String provinceId;
        private String countryId;
        private String postalCodeId;
        private String postalCode;
        private Double latitude;
        private Double longitude;
        private Boolean isDefault;
        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String addressLine1;
        private String addressLine2;
        private String wardId;
        private String districtId;
        private String provinceId;
        private String countryId;
        private String postalCodeId;
        private String postalCode;
        private Double latitude;
        private Double longitude;
        private Boolean isDefault;
        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private String addressId;
        private String addressType;
        private String addressLine1;
        private String wardId;
        private String districtId;
        private String provinceId;
        private String countryId;
        private Boolean isDefault;
        private Boolean isActive;
    }
}