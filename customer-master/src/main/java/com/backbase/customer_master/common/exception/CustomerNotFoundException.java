package com.backbase.customer_master.common.exception;

/**
 * Exception thrown when customer is not found
 */
public class CustomerNotFoundException extends CustomerException {
    public CustomerNotFoundException(String message) {
        super(message, "CUSTOMER_NOT_FOUND");
    }
}
