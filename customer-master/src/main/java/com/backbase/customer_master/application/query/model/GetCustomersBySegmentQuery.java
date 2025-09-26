package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable; /**
 * Query to get customers by segment
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomersBySegmentQuery {
    
    @NotBlank(message = "Customer segment is required")
    private String customerSegment;
    
    @NotNull(message = "Pageable is required")
    private Pageable pageable;
}
