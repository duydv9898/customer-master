package com.backbase.customer_master.common.exception;

/**
 * Exception thrown for forbidden access
 */
public class ForbiddenException extends CustomerException {
    public ForbiddenException(String message) {
        super(message, "FORBIDDEN");
    }
}
