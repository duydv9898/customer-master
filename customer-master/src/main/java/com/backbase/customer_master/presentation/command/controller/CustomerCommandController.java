package com.backbase.customer_master.presentation.command.controller;

import com.backbase.customer_master.application.command.handler.CustomerCommandHandler;
import com.backbase.customer_master.application.command.model.*;
import com.backbase.customer_master.application.command.service.CustomerCommandService;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST Controller for Customer Command operations (CQRS Pattern)
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Customer Commands", description = "Customer write operations (Create, Update, Delete)")
public class CustomerCommandController {

    private final CustomerCommandHandler customerCommandHandler;
    private final CustomerCommandService customerCommandService;

    @Operation(summary = "Create a new customer", description = "Creates a new customer record")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "409", description = "Customer already exists")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CreateCustomerCommand command) {
        log.info("Creating new customer");
//        CustomerDTO createdCustomer = customerCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCommandService.createCustomer(command));
    }

    @Operation(summary = "Update customer", description = "Updates an existing customer record")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "409", description = "Optimistic locking conflict")
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @Parameter(description = "Customer ID") @PathVariable String customerId,
            @Valid @RequestBody UpdateCustomerCommand command) {
        log.info("Updating customer: {}", customerId);
        // Ensure command has the correct customer ID
        command.setCustomerId(UUID.fromString(customerId));
//        CustomerDTO updatedCustomer = customerCommandHandler.handle(command);
        return ResponseEntity.ok(customerCommandService.updateCustomer(command));
    }

    @Operation(summary = "Update customer status", description = "Updates the CIF status of a customer")
    @ApiResponse(responseCode = "200", description = "Customer status updated successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PatchMapping("/{customerId}/status")
    public ResponseEntity<CustomerDTO> updateCustomerStatus(
            @Parameter(description = "Customer ID") @PathVariable String customerId,
            @Parameter(description = "New status") @RequestParam String status) {
        log.info("Updating customer status to: {} for customer: {}", status, customerId);
        
        UpdateCustomerStatusCommand command = UpdateCustomerStatusCommand.builder()
                .customerId(UUID.fromString(customerId))
                .status(status)
                .build();
        
//        CustomerDTO updatedCustomer = customerCommandHandler.handle(command);
        return ResponseEntity.ok(customerCommandService.updateCustomerStatus(command));
    }

    @Operation(summary = "Deactivate customer", description = "Deactivates a customer (soft delete)")
    @ApiResponse(responseCode = "204", description = "Customer deactivated successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PatchMapping("/{customerId}/deactivate")
    public ResponseEntity<Void> deactivateCustomer(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        log.info("Deactivating customer: {}", customerId);
        
        DeactivateCustomerCommand command = DeactivateCustomerCommand.builder()
                .customerId(UUID.fromString(customerId))
                .build();
        
//        customerCommandHandler.handle(command);
        customerCommandService.deactivateCustomer(command);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete customer", description = "Permanently deletes a customer (hard delete)")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        log.warn("Hard deleting customer: {}", customerId);
        
        DeleteCustomerCommand command = DeleteCustomerCommand.builder()
                .customerId(UUID.fromString(customerId))
                .build();
        
//        customerCommandHandler.handle(command);
        customerCommandService.deleteCustomer(command);
        return ResponseEntity.noContent().build();
    }
}