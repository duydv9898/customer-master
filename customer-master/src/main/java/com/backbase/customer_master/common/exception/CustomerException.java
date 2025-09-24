package com.backbase.customer_master.common.exception;

import lombok.Getter;

/**
 * Base exception for customer-related operations
 */
@Getter
public abstract class CustomerException extends RuntimeException {
    private final String errorCode;

    protected CustomerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected CustomerException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}

