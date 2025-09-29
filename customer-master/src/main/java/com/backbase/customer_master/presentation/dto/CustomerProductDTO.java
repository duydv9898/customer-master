package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Customer Product Data Transfer Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProductDTO {

    private String customerProductId;

    private String customerId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotBlank(message = "Product type is required")
    @Size(max = 50, message = "Product type must not exceed 50 characters")
    private String productType;

    @Size(max = 50, message = "Account number must not exceed 50 characters")
    private String accountNumber;

    @NotBlank(message = "Product status is required")
    @Size(max = 20, message = "Product status must not exceed 20 characters")
    private String productStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime openingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime closingDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Current balance must be positive")
    @Digits(integer = 17, fraction = 2, message = "Current balance format is invalid")
    private BigDecimal currentBalance;

    @DecimalMin(value = "0.0", inclusive = false, message = "Available balance must be positive")
    @Digits(integer = 17, fraction = 2, message = "Available balance format is invalid")
    private BigDecimal availableBalance;

    @DecimalMin(value = "0.0", message = "Credit limit must be non-negative")
    @Digits(integer = 17, fraction = 2, message = "Credit limit format is invalid")
    private BigDecimal creditLimit;

    @DecimalMin(value = "0.0", message = "Interest rate must be non-negative")
    @DecimalMax(value = "100.0", message = "Interest rate must not exceed 100%")
    @Digits(integer = 4, fraction = 4, message = "Interest rate format is invalid")
    private BigDecimal interestRate;

    @NotBlank(message = "Currency is required")
    @Size(max = 3, message = "Currency must be 3 characters")
    private String currency;

    private Boolean isPrimary;
    private String branchId;
    private String relationshipManagerId;
    private String notes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    private String createdBy;
    private String lastModifiedBy;

    @Schema(description = "updated by user")
    private String updatedBy;

    // Product information for display
    private String productName;
    private String branchName;
    private String relationshipManagerName;
}