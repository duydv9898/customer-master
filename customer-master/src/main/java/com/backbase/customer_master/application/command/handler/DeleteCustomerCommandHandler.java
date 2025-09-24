package com.backbase.customer_master.application.command.handler;

import com.backbase.customer_master.application.command.model.DeleteCustomerCommand;
import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.repository.CustomerRepository;
import com.backbase.customer_master.common.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Handler for DeleteCustomerCommand
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCustomerCommandHandler {

    private final CustomerRepository customerRepository;

    @Transactional
    public void handle(DeleteCustomerCommand command) {
        log.info("Handling DeleteCustomerCommand for ID: {}", command.getCustomerId());

        // Verify customer exists
        Optional<Customer> existingCustomer = customerRepository.findById(command.getCustomerId());
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with ID: " + command.getCustomerId());
        }

        // Soft delete - update status instead of hard delete
        Customer customer = existingCustomer.get();
        customer.updateStatus("DELETED");

        customerRepository.save(customer);

        log.info("Customer soft deleted successfully: {}", command.getCustomerId());
    }
}