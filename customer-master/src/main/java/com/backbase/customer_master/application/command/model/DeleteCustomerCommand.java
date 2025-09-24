package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Command model for deleting a customer
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCustomerCommand {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    private String reason;
    
    private String deletedBy;
}