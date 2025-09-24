package com.backbase.customer_master.common.exception;

/**
 * Exception thrown when identification is not found
 */
public class IdentificationNotFoundException extends CustomerException {
    public IdentificationNotFoundException(String message) {
        super(message, "IDENTIFICATION_NOT_FOUND");
    }
}
