package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable; /**
 * Query to get customers by type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomersByTypeQuery {
    
    @NotBlank(message = "Customer type is required")
    private String customerType;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}
