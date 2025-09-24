package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.GetCustomerByIdQuery;
import com.backbase.customer_master.presentation.dto.generated.model.CustomerResponse;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.application.mapper.CustomerMapper;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Handler for GetCustomerByIdQuery
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetCustomerByIdQueryHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse handle(GetCustomerByIdQuery query) {
        log.info("Handling GetCustomerByIdQuery for ID: {}", query.getCustomerId());

        Optional<Customer> customer;
        
        if (query.getIncludeAddresses() || query.getIncludeIdentifications() || 
            query.getIncludeRelationships() || query.getIncludeProducts()) {
            // Fetch with related entities
            customer = customerRepository.findByIdWithRelations(
                query.getCustomerId(),
                query.getIncludeAddresses(),
                query.getIncludeIdentifications(),
                query.getIncludeRelationships(),
                query.getIncludeProducts()
            );
        } else {
            // Simple fetch
            customer = customerRepository.findById(query.getCustomerId());
        }

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with ID: " + query.getCustomerId());
        }

        return customerMapper.toResponse(customer.get());
    }
}