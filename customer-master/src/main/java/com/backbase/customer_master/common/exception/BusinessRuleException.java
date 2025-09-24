package com.backbase.customer_master.common.exception;

/**
 * Exception thrown for business rule violations
 */
public class BusinessRuleException extends CustomerException {
    public BusinessRuleException(String message) {
        super(message, "BUSINESS_RULE_VIOLATION");
    }
}
