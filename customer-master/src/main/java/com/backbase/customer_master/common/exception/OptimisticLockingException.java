package com.backbase.customer_master.common.exception;

/**
 * Exception thrown for optimistic locking failures
 */
public class OptimisticLockingException extends CustomerException {
    public OptimisticLockingException(String message) {
        super(message, "OPTIMISTIC_LOCKING_FAILURE");
    }

    public OptimisticLockingException(String message, Throwable cause) {
        super(message, "OPTIMISTIC_LOCKING_FAILURE", cause);
    }
}
