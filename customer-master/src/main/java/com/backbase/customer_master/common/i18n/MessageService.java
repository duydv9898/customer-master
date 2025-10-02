package com.backbase.customer_master.common.i18n;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Service for retrieving internationalized messages
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageSource messageSource;

    /**
     * Get message by key with current locale
     *
     * @param key Message key enum
     * @return Localized message
     */
    public String getMessage(MessageKey key) {
        return getMessage(key.getKey(), null);
    }

    /**
     * Get message by key with parameters and current locale
     *
     * @param key Message key enum
     * @param args Message parameters
     * @return Localized message with parameters
     */
    public String getMessage(MessageKey key, Object... args) {
        return getMessage(key.getKey(), args);
    }

    /**
     * Get message by key string with current locale
     *
     * @param key Message key string
     * @param args Message parameters
     * @return Localized message
     */
    public String getMessage(String key, Object... args) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            log.error("Error getting message for key: {}", key, e);
            return key;
        }
    }

    /**
     * Get message with specific locale
     *
     * @param key Message key enum
     * @param locale Specific locale
     * @return Localized message
     */
    public String getMessage(MessageKey key, Locale locale) {
        return getMessage(key.getKey(), locale, null);
    }

    /**
     * Get message with parameters and specific locale
     *
     * @param key Message key enum
     * @param locale Specific locale
     * @param args Message parameters
     * @return Localized message with parameters
     */
    public String getMessage(MessageKey key, Locale locale, Object... args) {
        return getMessage(key.getKey(), locale, args);
    }

    /**
     * Get message by key string with specific locale
     *
     * @param key Message key string
     * @param locale Specific locale
     * @param args Message parameters
     * @return Localized message
     */
    public String getMessage(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            log.error("Error getting message for key: {} with locale: {}", key, locale, e);
            return key;
        }
    }

    /**
     * Get message with default value if key not found
     *
     * @param key Message key enum
     * @param defaultMessage Default message if key not found
     * @return Localized message or default
     */
    public String getMessageOrDefault(MessageKey key, String defaultMessage) {
        return getMessageOrDefault(key.getKey(), defaultMessage, null);
    }

    /**
     * Get message with parameters and default value if key not found
     *
     * @param key Message key enum
     * @param defaultMessage Default message if key not found
     * @param args Message parameters
     * @return Localized message or default
     */
    public String getMessageOrDefault(MessageKey key, String defaultMessage, Object... args) {
        return getMessageOrDefault(key.getKey(), defaultMessage, args);
    }

    /**
     * Get message by key string with default value
     *
     * @param key Message key string
     * @param defaultMessage Default message if key not found
     * @param args Message parameters
     * @return Localized message or default
     */
    public String getMessageOrDefault(String key, String defaultMessage, Object... args) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, args, defaultMessage, locale);
        } catch (Exception e) {
            log.error("Error getting message for key: {}", key, e);
            return defaultMessage;
        }
    }

    /**
     * Get current locale
     *
     * @return Current locale
     */
    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * Check if current locale is Vietnamese
     *
     * @return true if Vietnamese, false otherwise
     */
    public boolean isVietnamese() {
        return "vi".equals(getCurrentLocale().getLanguage());
    }

    /**
     * Check if current locale is English
     *
     * @return true if English, false otherwise
     */
    public boolean isEnglish() {
        return "en".equals(getCurrentLocale().getLanguage());
    }
}