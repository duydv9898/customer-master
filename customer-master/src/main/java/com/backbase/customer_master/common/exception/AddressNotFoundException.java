package com.backbase.customer_master.common.exception;

/**
 * Exception thrown when address is not found
 */
public class AddressNotFoundException extends CustomerException {
    public AddressNotFoundException(String message) {
        super(message, "ADDRESS_NOT_FOUND");
    }
}
