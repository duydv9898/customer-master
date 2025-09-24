package com.backbase.customer_master.presentation.command.controller;

import com.backbase.customer_master.presentation.dto.generated.model.*;
import com.backbase.customer_master.application.command.handler.CreateCustomerCommandHandler;
import com.backbase.customer_master.application.command.handler.UpdateCustomerCommandHandler;
import com.backbase.customer_master.application.command.handler.DeleteCustomerCommandHandler;
import com.backbase.customer_master.application.command.model.CreateCustomerCommand;
import com.backbase.customer_master.application.command.model.UpdateCustomerCommand;
import com.backbase.customer_master.application.command.model.DeleteCustomerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * REST Controller xử lý các yêu cầu Command (POST, PUT, DELETE) cho Customer
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerCommandController {

    private final CreateCustomerCommandHandler createCustomerCommandHandler;
    private final UpdateCustomerCommandHandler updateCustomerCommandHandler;
    private final DeleteCustomerCommandHandler deleteCustomerCommandHandler;

    public ResponseEntity<CustomerResponse> createCustomer(CreateCustomerRequest request) {
        log.info("Processing create customer command for: {}", request.getFullName());
        
        try {
            CreateCustomerCommand command = CreateCustomerCommand.builder()
                .customerType(request.getCustomerType())
                .fullName(request.getFullName())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .placeOfBirth(request.getPlaceOfBirth())
                .genderId(request.getGenderId())
                .maritalStatusId(request.getMaritalStatusId())
                .nationalityId(request.getNationalityId())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .branchId(request.getBranchId())
                .build();

            CustomerResponse response = createCustomerCommandHandler.handle(command);
            
            log.info("Customer created successfully with ID: {}", response.getCustomerId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            log.error("Error creating customer: ", e);
            throw e;
        }
    }

    public ResponseEntity<CustomerResponse> updateCustomer(String customerId, UpdateCustomerRequest request) {
        log.info("Processing update customer command for ID: {}", customerId);
        
        try {
            UpdateCustomerCommand command = UpdateCustomerCommand.builder()
                .customerId(customerId)
                .fullName(request.getFullName())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .placeOfBirth(request.getPlaceOfBirth())
                .maritalStatusId(request.getMaritalStatusId())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .versionNo(request.getVersionNo())
                .build();

            CustomerResponse response = updateCustomerCommandHandler.handle(command);
            
            log.info("Customer updated successfully: {}", customerId);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Error updating customer: ", e);
            throw e;
        }
    }

    public ResponseEntity<Void> deleteCustomer(String customerId) {
        log.info("Processing delete customer command for ID: {}", customerId);
        
        try {
            DeleteCustomerCommand command = DeleteCustomerCommand.builder()
                .customerId(customerId)
                .build();

            deleteCustomerCommandHandler.handle(command);
            
            log.info("Customer deleted successfully: {}", customerId);
            return ResponseEntity.noContent().build();
            
        } catch (Exception e) {
            log.error("Error deleting customer: ", e);
            throw e;
        }
    }
}