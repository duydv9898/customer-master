package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable; /**
 * Query to search customers by name
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomersByNameQuery {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}
