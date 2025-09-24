package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Query model for getting a customer by ID
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByIdQuery {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @Builder.Default
    private Boolean includeAddresses = false;

    @Builder.Default
    private Boolean includeIdentifications = false;

    @Builder.Default
    private Boolean includeRelationships = false;

    @Builder.Default
    private Boolean includeProducts = false;
}
