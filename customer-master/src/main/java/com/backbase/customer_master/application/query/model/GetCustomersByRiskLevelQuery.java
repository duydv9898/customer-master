package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to get customers by risk level
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomersByRiskLevelQuery {
    
    @NotBlank(message = "Risk level is required")
    private String riskLevel;
}
