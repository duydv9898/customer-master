package com.backbase.customer_master.application.query.model;

import lombok.*;

/**
 * Query model for getting all customers with pagination and filtering
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCustomersQuery {

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 20;

    private String customerId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cifStatus;
    private String branchId;
    private String customerType;
    private String genderId;
    private String nationalityId;

    private String sortBy;
    private String sortDirection;
}