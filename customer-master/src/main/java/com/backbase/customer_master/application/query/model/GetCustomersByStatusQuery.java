package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to get customers by status
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomersByStatusQuery {
    
    @NotBlank(message = "Status is required")
    private String status;
}
