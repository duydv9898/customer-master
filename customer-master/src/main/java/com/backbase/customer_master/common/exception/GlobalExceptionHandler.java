package com.backbase.customer_master.common.exception;

import com.backbase.customer_master.common.i18n.MessageKey;
import com.backbase.customer_master.common.i18n.MessageService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler with i18n support
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageService messageService;

    @Data
    @Builder
    public static class ErrorResponse {
        private String code;
        private String message;
        private String details;
        private LocalDateTime timestamp;
        private String path;
        private Map<String, String> validationErrors;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(
            CustomerNotFoundException ex, WebRequest request) {

        log.warn("Customer not found: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.ERROR_CUSTOMER_NOT_FOUND,
                ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExists(
            CustomerAlreadyExistsException ex, WebRequest request) {

        log.warn("Customer already exists: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.ERROR_CUSTOMER_ALREADY_EXISTS);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFound(
            AddressNotFoundException ex, WebRequest request) {

        log.warn("Address not found: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.ERROR_ADDRESS_NOT_FOUND,
                ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IdentificationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdentificationNotFound(
            IdentificationNotFoundException ex, WebRequest request) {

        log.warn("Identification not found: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.ERROR_IDENTIFICATION_NOT_FOUND,
                ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(OptimisticLockingException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLocking(
            OptimisticLockingException ex, WebRequest request) {

        log.warn("Optimistic locking failure: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.BUSINESS_OPTIMISTIC_LOCK);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .details(messageService.getMessage(MessageKey.BUSINESS_OPTIMISTIC_LOCK))
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            ValidationException ex, WebRequest request) {

        log.warn("Validation error: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRule(
            BusinessRuleException ex, WebRequest request) {

        log.warn("Business rule violation: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            UnauthorizedException ex, WebRequest request) {

        log.warn("Unauthorized access: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.SYSTEM_UNAUTHORIZED);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(
            ForbiddenException ex, WebRequest request) {

        log.warn("Forbidden access: {}", ex.getMessage());

        String localizedMessage = messageService.getMessage(
                MessageKey.SYSTEM_FORBIDDEN);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        log.warn("Validation errors: {}", ex.getMessage());

        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        String localizedMessage = messageService.getMessage(
                MessageKey.VALIDATION_INVALID_FORMAT, "request data");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("VALIDATION_ERROR")
                .message(localizedMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        log.error("Unexpected error occurred", ex);

        String localizedMessage = messageService.getMessage(
                MessageKey.SYSTEM_ERROR_INTERNAL);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message(localizedMessage)
                .details(messageService.getMessage(MessageKey.SYSTEM_ERROR_INTERNAL))
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}