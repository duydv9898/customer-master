package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Query model for getting customer addresses
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerAddressesQuery {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    private String addressType;

    @Builder.Default
    private Boolean activeOnly = true;
}