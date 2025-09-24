package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Query model for getting customer identifications
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerIdentificationsQuery {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    private String identificationTypeId;

    @Builder.Default
    private Boolean activeOnly = true;

    @Builder.Default
    private Boolean verifiedOnly = false;
}