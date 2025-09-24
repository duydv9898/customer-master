package com.backbase.customer_master.common.exception;

/**
 * Exception thrown for validation errors
 */
public class ValidationException extends CustomerException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}
