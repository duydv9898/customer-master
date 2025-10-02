package com.backbase.customer_master.common.i18n;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Message keys for internationalization
 * Each enum constant represents a message that can be translated
 */
@Getter
@RequiredArgsConstructor
public enum MessageKey {
    
    // ============================================
    // SUCCESS MESSAGES
    // ============================================
    SUCCESS_CUSTOMER_CREATED("success.customer.created"),
    SUCCESS_CUSTOMER_UPDATED("success.customer.updated"),
    SUCCESS_CUSTOMER_DELETED("success.customer.deleted"),
    SUCCESS_CUSTOMER_DEACTIVATED("success.customer.deactivated"),
    SUCCESS_CUSTOMER_STATUS_UPDATED("success.customer.status.updated"),
    SUCCESS_ADDRESS_CREATED("success.address.created"),
    SUCCESS_ADDRESS_UPDATED("success.address.updated"),
    SUCCESS_IDENTIFICATION_CREATED("success.identification.created"),
    SUCCESS_IDENTIFICATION_UPDATED("success.identification.updated"),
    
    // ============================================
    // ERROR MESSAGES - CUSTOMER
    // ============================================
    ERROR_CUSTOMER_NOT_FOUND("error.customer.not.found"),
    ERROR_CUSTOMER_ALREADY_EXISTS("error.customer.already.exists"),
    ERROR_CUSTOMER_EMAIL_EXISTS("error.customer.email.exists"),
    ERROR_CUSTOMER_PHONE_EXISTS("error.customer.phone.exists"),
    ERROR_CUSTOMER_TAX_FILE_EXISTS("error.customer.tax.file.exists"),
    ERROR_CUSTOMER_INVALID_STATUS("error.customer.invalid.status"),
    ERROR_CUSTOMER_CANNOT_DELETE("error.customer.cannot.delete"),
    ERROR_CUSTOMER_INVALID_ID("error.customer.invalid.id"),
    
    // ============================================
    // ERROR MESSAGES - ADDRESS
    // ============================================
    ERROR_ADDRESS_NOT_FOUND("error.address.not.found"),
    ERROR_ADDRESS_INVALID_COUNTRY("error.address.invalid.country"),
    ERROR_ADDRESS_INVALID_PROVINCE("error.address.invalid.province"),
    ERROR_ADDRESS_INVALID_DISTRICT("error.address.invalid.district"),
    
    // ============================================
    // ERROR MESSAGES - IDENTIFICATION
    // ============================================
    ERROR_IDENTIFICATION_NOT_FOUND("error.identification.not.found"),
    ERROR_IDENTIFICATION_ALREADY_EXISTS("error.identification.already.exists"),
    ERROR_IDENTIFICATION_EXPIRED("error.identification.expired"),
    ERROR_IDENTIFICATION_INVALID_TYPE("error.identification.invalid.type"),
    
    // ============================================
    // ERROR MESSAGES - REFERENCE DATA
    // ============================================
    ERROR_GENDER_NOT_FOUND("error.gender.not.found"),
    ERROR_COUNTRY_NOT_FOUND("error.country.not.found"),
    ERROR_MARITAL_STATUS_NOT_FOUND("error.marital.status.not.found"),
    ERROR_CLIENT_TYPE_NOT_FOUND("error.client.type.not.found"),
    ERROR_CATEGORY_NOT_FOUND("error.category.not.found"),
    ERROR_OCCUPATION_NOT_FOUND("error.occupation.not.found"),
    ERROR_INDUSTRY_NOT_FOUND("error.industry.not.found"),
    ERROR_BUSINESS_CLASS_NOT_FOUND("error.business.class.not.found"),
    ERROR_SECTOR_NOT_FOUND("error.sector.not.found"),
    ERROR_LANGUAGE_NOT_FOUND("error.language.not.found"),
    ERROR_CONTACT_CHANNEL_NOT_FOUND("error.contact.channel.not.found"),
    ERROR_CUSTOMER_SEGMENT_NOT_FOUND("error.customer.segment.not.found"),
    
    // ============================================
    // VALIDATION MESSAGES
    // ============================================
    VALIDATION_REQUIRED_FIELD("validation.required.field"),
    VALIDATION_INVALID_EMAIL("validation.invalid.email"),
    VALIDATION_INVALID_PHONE("validation.invalid.phone"),
    VALIDATION_INVALID_DATE("validation.invalid.date"),
    VALIDATION_DATE_FUTURE("validation.date.future"),
    VALIDATION_DATE_PAST("validation.date.past"),
    VALIDATION_AGE_MINIMUM("validation.age.minimum"),
    VALIDATION_AGE_MAXIMUM("validation.age.maximum"),
    VALIDATION_LENGTH_MIN("validation.length.min"),
    VALIDATION_LENGTH_MAX("validation.length.max"),
    VALIDATION_INVALID_FORMAT("validation.invalid.format"),
    VALIDATION_INVALID_VALUE("validation.invalid.value"),
    
    // ============================================
    // BUSINESS RULE MESSAGES
    // ============================================
    BUSINESS_KYC_REQUIRED("business.kyc.required"),
    BUSINESS_KYC_FAILED("business.kyc.failed"),
    BUSINESS_KYC_PENDING("business.kyc.pending"),
    BUSINESS_CUSTOMER_INACTIVE("business.customer.inactive"),
    BUSINESS_CUSTOMER_SUSPENDED("business.customer.suspended"),
    BUSINESS_CUSTOMER_CLOSED("business.customer.closed"),
    BUSINESS_DUPLICATE_EMAIL("business.duplicate.email"),
    BUSINESS_DUPLICATE_PHONE("business.duplicate.phone"),
    BUSINESS_OPTIMISTIC_LOCK("business.optimistic.lock"),
    
    // ============================================
    // SYSTEM MESSAGES
    // ============================================
    SYSTEM_ERROR_INTERNAL("system.error.internal"),
    SYSTEM_ERROR_DATABASE("system.error.database"),
    SYSTEM_ERROR_NETWORK("system.error.network"),
    SYSTEM_ERROR_TIMEOUT("system.error.timeout"),
    SYSTEM_UNAUTHORIZED("system.unauthorized"),
    SYSTEM_FORBIDDEN("system.forbidden"),
    SYSTEM_MAINTENANCE("system.maintenance"),
    
    // ============================================
    // INFO MESSAGES
    // ============================================
    INFO_CUSTOMER_SEARCH_NO_RESULTS("info.customer.search.no.results"),
    INFO_CUSTOMER_PENDING_APPROVAL("info.customer.pending.approval"),
    INFO_CUSTOMER_DOCUMENT_UPLOADED("info.customer.document.uploaded"),
    INFO_CUSTOMER_PROFILE_INCOMPLETE("info.customer.profile.incomplete"),
    
    // ============================================
    // WARNING MESSAGES
    // ============================================
    WARNING_IDENTIFICATION_EXPIRING_SOON("warning.identification.expiring.soon"),
    WARNING_CUSTOMER_DATA_OUTDATED("warning.customer.data.outdated"),
    WARNING_PERIODIC_REVIEW_DUE("warning.periodic.review.due"),
    
    // ============================================
    // NOTIFICATION MESSAGES
    // ============================================
    NOTIFICATION_PROFILE_UPDATED("notification.profile.updated"),
    NOTIFICATION_STATUS_CHANGED("notification.status.changed"),
    NOTIFICATION_DOCUMENT_APPROVED("notification.document.approved"),
    NOTIFICATION_DOCUMENT_REJECTED("notification.document.rejected");
    
    private final String key;
    
    /**
     * Get the message key as string
     * @return message key
     */
    @Override
    public String toString() {
        return this.key;
    }
}