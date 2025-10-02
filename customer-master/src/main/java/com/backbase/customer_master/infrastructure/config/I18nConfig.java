package com.backbase.customer_master.infrastructure.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

/**
 * Configuration for internationalization (i18n)
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

    /**
     * Configure MessageSource for loading message properties files
     *
     * @return MessageSource bean
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(3600); // Cache for 1 hour
        return messageSource;
    }

    /**
     * Configure LocaleResolver to determine the current locale
     * Uses Accept-Language header from HTTP request
     *
     * @return LocaleResolver bean
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setSupportedLocales(Arrays.asList(
                new Locale("en"), // English
                new Locale("vi")  // Vietnamese
        ));
        localeResolver.setDefaultLocale(new Locale("en")); // Default to English
        return localeResolver;
    }

    /**
     * Configure LocaleChangeInterceptor to allow changing locale via request parameter
     * Example: ?lang=vi or ?lang=en
     *
     * @return LocaleChangeInterceptor bean
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // Request parameter name
        return interceptor;
    }

    /**
     * Add the locale change interceptor to the registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}