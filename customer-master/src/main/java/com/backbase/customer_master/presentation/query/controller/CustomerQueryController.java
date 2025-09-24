package com.backbase.customer_master.presentation.query.controller;

import com.backbase.customer_master.presentation.dto.generated.model.*;
import com.backbase.customer_master.application.query.handler.GetAllCustomersQueryHandler;
import com.backbase.customer_master.application.query.handler.GetCustomerByIdQueryHandler;
import com.backbase.customer_master.application.query.model.GetAllCustomersQuery;
import com.backbase.customer_master.application.query.model.GetCustomerByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * REST Controller xử lý các yêu cầu Query (GET) cho Customer
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {

    private final GetAllCustomersQueryHandler getAllCustomersQueryHandler;
    private final GetCustomerByIdQueryHandler getCustomerByIdQueryHandler;

    public ResponseEntity<CustomerPageResponse> getAllCustomers(Integer page, Integer size, String customerId, String fullName) {
        log.info("Processing get all customers query - page: {}, size: {}", page, size);
        
        GetAllCustomersQuery query = GetAllCustomersQuery.builder()
            .page(page != null ? page : 0)
            .size(size != null ? size : 20)
            .customerId(customerId)
            .fullName(fullName)
            .build();

        CustomerPageResponse response = getAllCustomersQueryHandler.handle(query);
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<CustomerResponse> getCustomerById(String customerId) {
        log.info("Processing get customer by ID query: {}", customerId);
        
        GetCustomerByIdQuery query = GetCustomerByIdQuery.builder()
            .customerId(customerId)
            .build();

        CustomerResponse response = getCustomerByIdQueryHandler.handle(query);
        
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(response);
    }
}