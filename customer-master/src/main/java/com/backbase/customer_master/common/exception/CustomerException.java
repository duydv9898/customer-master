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

/**
 * Exception thrown when customer is not found
 */
class CustomerNotFoundException extends CustomerException {
    public CustomerNotFoundException(String message) {
        super(message, "CUSTOMER_NOT_FOUND");
    }
}

/**
 * Exception thrown when customer already exists
 */
class CustomerAlreadyExistsException extends CustomerException {
    public CustomerAlreadyExistsException(String message) {
        super(message, "CUSTOMER_ALREADY_EXISTS");
    }
}

/**
 * Exception thrown when address is not found
 */
class AddressNotFoundException extends CustomerException {
    public AddressNotFoundException(String message) {
        super(message, "ADDRESS_NOT_FOUND");
    }
}

/**
 * Exception thrown when identification is not found
 */
class IdentificationNotFoundException extends CustomerException {
    public IdentificationNotFoundException(String message) {
        super(message, "IDENTIFICATION_NOT_FOUND");
    }
}

/**
 * Exception thrown for optimistic locking failures
 */
class OptimisticLockingException extends CustomerException {
    public OptimisticLockingException(String message) {
        super(message, "OPTIMISTIC_LOCKING_FAILURE");
    }
    
    public OptimisticLockingException(String message, Throwable cause) {
        super(message, "OPTIMISTIC_LOCKING_FAILURE", cause);
    }
}

/**
 * Exception thrown for validation errors
 */
class ValidationException extends CustomerException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}

/**
 * Exception thrown for business rule violations
 */
class BusinessRuleException extends CustomerException {
    public BusinessRuleException(String message) {
        super(message, "BUSINESS_RULE_VIOLATION");
    }
}

/**
 * Exception thrown for unauthorized access
 */
class UnauthorizedException extends CustomerException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED");
    }
}

/**
 * Exception thrown for forbidden access
 */
class ForbiddenException extends CustomerException {
    public ForbiddenException(String message) {
        super(message, "FORBIDDEN");
    }
}