package com.backbase.customer_master.common.exception;

/**
 * Exception thrown when customer already exists
 */
public class CustomerAlreadyExistsException extends CustomerException {
    public CustomerAlreadyExistsException(String message) {
        super(message, "CUSTOMER_ALREADY_EXISTS");
    }
}
