package com.backbase.customer_master.application.command.service;

import com.backbase.customer_master.application.command.handler.CustomerCommandHandler;
import com.backbase.customer_master.application.command.model.*;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Command Service for Customer operations (CQRS Pattern)
 * Acts as a facade/orchestrator for command operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerCommandService {

    private final CustomerCommandHandler customerCommandHandler;

    /**
     * Create a new customer
     */
    public CustomerDTO createCustomer(CreateCustomerCommand command) {
        log.info("Processing create customer command for: {}", command.getEmail());
        return customerCommandHandler.handle(command);
    }

    /**
     * Update an existing customer
     */
    public CustomerDTO updateCustomer(UpdateCustomerCommand command) {
        log.info("Processing update customer command for ID: {}", command.getCustomerId());
        return customerCommandHandler.handle(command);
    }

    /**
     * Update customer status
     */
    public CustomerDTO updateCustomerStatus(UpdateCustomerStatusCommand command) {
        log.info("Processing update customer status command for ID: {}", command.getCustomerId());
        return customerCommandHandler.handle(command);
    }

    /**
     * Deactivate customer
     */
    public void deactivateCustomer(DeactivateCustomerCommand command) {
        log.info("Processing deactivate customer command for ID: {}", command.getCustomerId());
        customerCommandHandler.handle(command);
    }

    /**
     * Delete customer
     */
    public void deleteCustomer(DeleteCustomerCommand command) {
        log.info("Processing delete customer command for ID: {}", command.getCustomerId());
        customerCommandHandler.handle(command);
    }
}