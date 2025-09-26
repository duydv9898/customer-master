package com.backbase.customer_master.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main application configuration
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.backbase.customer_master.domain.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class ApplicationConfig {

    /**
     * OpenAPI configuration for Swagger documentation
     */
    @Bean
    public OpenAPI customerMasterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Master API")
                        .description("Customer Master Database Management System - RESTful API following CQRS pattern for managing customer information, addresses, identifications, and related data")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Backbase Development Team")
                                .email("dev@backbase.com")
                                .url("https://www.backbase.com"))
                        .license(new License()
                                .name("Proprietary")
                                .url("https://www.backbase.com/license")));
    }

    /**
     * CORS configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}

/**
 * Application properties configuration
 */
@Configuration
@ConfigurationProperties(prefix = "app")
class ApplicationProperties {
    
    private Security security = new Security();
    private Audit audit = new Audit();
    private Validation validation = new Validation();
    
    // Getters and setters
    public Security getSecurity() { return security; }
    public void setSecurity(Security security) { this.security = security; }
    
    public Audit getAudit() { return audit; }
    public void setAudit(Audit audit) { this.audit = audit; }
    
    public Validation getValidation() { return validation; }
    public void setValidation(Validation validation) { this.validation = validation; }
    
    public static class Security {
        private boolean enableEncryption = true;
        private String hashAlgorithm = "SHA-256";
        
        public boolean isEnableEncryption() { return enableEncryption; }
        public void setEnableEncryption(boolean enableEncryption) { this.enableEncryption = enableEncryption; }
        
        public String getHashAlgorithm() { return hashAlgorithm; }
        public void setHashAlgorithm(String hashAlgorithm) { this.hashAlgorithm = hashAlgorithm; }
    }
    
    public static class Audit {
        private boolean enableAuditTrail = true;
        private int retentionDays = 2555; // 7 years
        
        public boolean isEnableAuditTrail() { return enableAuditTrail; }
        public void setEnableAuditTrail(boolean enableAuditTrail) { this.enableAuditTrail = enableAuditTrail; }
        
        public int getRetentionDays() { return retentionDays; }
        public void setRetentionDays(int retentionDays) { this.retentionDays = retentionDays; }
    }
    
    public static class Validation {
        private int minAge = 18;
        private int maxAge = 120;
        private int expiryWarningDays = 30;
        
        public int getMinAge() { return minAge; }
        public void setMinAge(int minAge) { this.minAge = minAge; }
        
        public int getMaxAge() { return maxAge; }
        public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
        
        public int getExpiryWarningDays() { return expiryWarningDays; }
        public void setExpiryWarningDays(int expiryWarningDays) { this.expiryWarningDays = expiryWarningDays; }
    }
}