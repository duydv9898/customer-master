package com.backbase.customer_master.application.query.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Query to find customer by identification number
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCustomerByIdentificationNumberQuery {
    
    @NotBlank(message = "Identification number is required")
    private String identificationNumber;
}
