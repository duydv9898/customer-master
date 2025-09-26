package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to find customer by phone number
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCustomerByPhoneNumberQuery {
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
