package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to find customer by email
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCustomerByEmailQuery {
    
    @NotBlank(message = "Email is required")
    private String email;
}
