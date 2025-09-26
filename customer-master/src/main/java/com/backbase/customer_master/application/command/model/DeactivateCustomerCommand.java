package com.backbase.customer_master.application.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; /**
 * Command to deactivate customer (soft delete)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateCustomerCommand {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    private String lastModifiedBy;
}
