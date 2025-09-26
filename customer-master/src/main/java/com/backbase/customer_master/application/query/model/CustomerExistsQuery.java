package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to check if customer exists
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerExistsQuery {
    
    @NotBlank(message = "Customer ID is required")
    private String customerId;
}
