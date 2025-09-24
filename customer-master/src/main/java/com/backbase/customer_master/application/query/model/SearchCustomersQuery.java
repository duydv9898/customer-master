package com.backbase.customer_master.application.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomersQuery {

    private String keyword; // Search in multiple fields
    private String customerType;
    private String cifStatus;
    private String branchId;
    private String genderId;
    private String nationalityId;
    private String maritalStatusId;
    
    private LocalDate dateOfBirthFrom;
    private LocalDate dateOfBirthTo;
    
    private String customerSegment;
    private String relationshipManagerId;
    private String riskLevel;
    private String kycStatus;
    
    private Boolean isPep;
    private Boolean isSanctionsList;
    
    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 20;

    private String sortBy;
    private String sortDirection;
}