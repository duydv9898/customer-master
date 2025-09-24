package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.GetAllCustomersQuery;
import com.backbase.customer_master.presentation.dto.generated.model.CustomerPageResponse;
import com.backbase.customer_master.presentation.dto.generated.model.CustomerResponse;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.application.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Handler for GetAllCustomersQuery
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersQueryHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerPageResponse handle(GetAllCustomersQuery query) {
        log.info("Handling GetAllCustomersQuery - page: {}, size: {}", query.getPage(), query.getSize());

        // Build pageable with sorting
        Sort sort = buildSort(query.getSortBy(), query.getSortDirection());
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), sort);

        // Execute query based on filters
        Page<Customer> customers = executeQuery(query, pageable);

        // Convert to response DTO
        return CustomerPageResponse.builder()
            .content(customers.getContent().stream()
                .map(customerMapper::toResponse)
                .toList())
            .totalElements(customers.getTotalElements())
            .totalPages(customers.getTotalPages())
            .size(customers.getSize())
            .number(customers.getNumber())
            .first(customers.isFirst())
            .last(customers.isLast())
            .build();
    }

    private Page<Customer> executeQuery(GetAllCustomersQuery query, Pageable pageable) {
        if (query.getCustomerId() != null || query.getFullName() != null) {
            return customerRepository.findByCustomerIdContainingIgnoreCaseOrFullNameContainingIgnoreCase(
                query.getCustomerId() != null ? query.getCustomerId() : "",
                query.getFullName() != null ? query.getFullName() : "",
                pageable
            );
        } else if (query.getBranchId() != null) {
            return customerRepository.findByBranchId(query.getBranchId(), pageable);
        } else if (query.getCifStatus() != null) {
            return customerRepository.findByCifStatus(query.getCifStatus(), pageable);
        } else {
            return customerRepository.findAll(pageable);
        }
    }

    private Sort buildSort(String sortBy, String sortDirection) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "createdDate";
        }
        
        Sort.Direction direction = Sort.Direction.DESC;
        if ("asc".equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.ASC;
        }
        
        return Sort.by(direction, sortBy);
    }
}