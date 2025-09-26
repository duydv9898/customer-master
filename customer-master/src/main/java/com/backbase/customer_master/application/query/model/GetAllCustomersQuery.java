package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable; /**
 * Query to get all customers with pagination
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCustomersQuery {
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}
