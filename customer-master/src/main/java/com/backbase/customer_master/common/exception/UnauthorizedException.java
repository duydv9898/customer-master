package com.backbase.customer_master.common.exception;

/**
 * Exception thrown for unauthorized access
 */
public class UnauthorizedException extends CustomerException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED");
    }
}
