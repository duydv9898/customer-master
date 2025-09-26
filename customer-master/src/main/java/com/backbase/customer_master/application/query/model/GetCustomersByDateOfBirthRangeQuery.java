package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate; /**
 * Query to get customers by date of birth range
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomersByDateOfBirthRangeQuery {
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
}
