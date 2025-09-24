package com.backbase.customer_master.presentation.dto;

import lombok.*;

/**
 * Reference Data DTO classes for API layer
 */
public class ReferenceDataDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CountryResponse {
        private String countryId;
        private String countryCode;
        private String countryName;
        private String countryNameEn;
        private String region;
        private String currencyCode;
        private String phoneCode;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProvinceResponse {
        private String provinceId;
        private String provinceCode;
        private String provinceName;
        private String countryId;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DistrictResponse {
        private String districtId;
        private String districtCode;
        private String districtName;
        private String districtType;
        private String provinceId;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WardResponse {
        private String wardId;
        private String wardCode;
        private String wardName;
        private String wardType;
        private String districtId;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GenderResponse {
        private String genderId;
        private String genderCode;
        private String genderName;
        private String genderNameEn;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MaritalStatusResponse {
        private String maritalStatusId;
        private String maritalStatusCode;
        private String maritalStatusName;
        private String maritalStatusNameEn;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClientTypeResponse {
        private String clientTypeId;
        private String clientTypeCode;
        private String clientTypeName;
        private String clientTypeNameEn;
        private String description;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BranchResponse {
        private String branchId;
        private String branchCode;
        private String branchName;
        private String branchNameEn;
        private String branchType;
        private String address;
        private String phoneNumber;
        private String email;
        private String managerName;
        private Boolean isActive;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdentificationTypeResponse {
        private String identificationTypeId;
        private String identificationTypeCode;
        private String identificationTypeName;
        private String identificationTypeNameEn;
        private String description;
        private Integer validityPeriodYears;
        private Boolean requiresExpiry;
        private Boolean isActive;
        private Integer sortOrder;
    }
}