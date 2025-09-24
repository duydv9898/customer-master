package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Customer DTO classes for API layer
 */
public class CustomerDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String customerId;
        private String customerType;
        private String fullName;
        private String firstName;
        private String middleName;
        private String lastName;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateOfBirth;
        
        private String placeOfBirth;
        private String genderId;
        private String maritalStatusId;
        private String nationalityId;
        private String email;
        private String phoneNumber;
        private String secondaryPhone;
        private String occupation;
        private String educationLevel;
        private String incomeRange;
        private String cifStatus;
        private String riskLevel;
        private Boolean isPep;
        private Boolean isSanctionsList;
        private String kycStatus;
        private String branchId;
        private String customerSegment;
        private String relationshipManagerId;
        private String taxId;
        private String sourceOfFund;
        private String referralSource;
        private String languagePreference;
        private String communicationPreference;
        private Boolean isConsentMarketing;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime consentDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastContactDate;
        
        private String notes;
        private Long versionNo;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastModifiedDate;
        
        private String createdBy;
        private String lastModifiedBy;
        
        // Nested objects
        private List<AddressDto.Response> addresses;
        private List<IdentificationDto.Response> identifications;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private String customerType;
        private String fullName;
        private String firstName;
        private String middleName;
        private String lastName;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateOfBirth;
        
        private String placeOfBirth;
        private String genderId;
        private String maritalStatusId;
        private String nationalityId;
        private String email;
        private String phoneNumber;
        private String secondaryPhone;
        private String occupation;
        private String educationLevel;
        private String incomeRange;
        private String branchId;
        private String customerSegment;
        private String relationshipManagerId;
        private String taxId;
        private String sourceOfFund;
        private String referralSource;
        private String languagePreference;
        private String communicationPreference;
        private Boolean isConsentMarketing;
        private String notes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String fullName;
        private String firstName;
        private String middleName;
        private String lastName;
        private String placeOfBirth;
        private String maritalStatusId;
        private String email;
        private String phoneNumber;
        private String secondaryPhone;
        private String occupation;
        private String educationLevel;
        private String incomeRange;
        private String customerSegment;
        private String relationshipManagerId;
        private String taxId;
        private String sourceOfFund;
        private String referralSource;
        private String languagePreference;
        private String communicationPreference;
        private Boolean isConsentMarketing;
        private String notes;
        private Long versionNo; // For optimistic locking
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private String customerId;
        private String fullName;
        private String customerType;
        private String cifStatus;
        private String email;
        private String phoneNumber;
        private String branchId;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchRequest {
        private String customerId;
        private String fullName;
        private String email;
        private String phoneNumber;
        private String cifStatus;
        private String branchId;
        private String customerType;
        private String genderId;
        private String nationalityId;
        private Integer page;
        private Integer size;
        private String sortBy;
        private String sortDirection;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {
        private List<Response> content;
        private Long totalElements;
        private Integer totalPages;
        private Integer size;
        private Integer number;
        private Boolean first;
        private Boolean last;
    }
}