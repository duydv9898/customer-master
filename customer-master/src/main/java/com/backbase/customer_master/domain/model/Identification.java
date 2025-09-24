package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Identification entity for customer identification documents
 */
@Entity
@Table(name = "identification")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Identification {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "identification_id", length = 36)
    @EqualsAndHashCode.Include
    private String identificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "identification_type_id", length = 36, nullable = false)
    private String identificationTypeId;

    @Column(name = "identification_number", length = 50, nullable = false)
    private String identificationNumber;

    @Column(name = "identification_number_hash", length = 255)
    private String identificationNumberHash;

    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "issuing_authority", length = 200, nullable = false)
    private String issuingAuthority;

    @Column(name = "issuing_country_id", length = 36, nullable = false)
    private String issuingCountryId;

    @Column(name = "issuing_province_id", length = 36)
    private String issuingProvinceId;

    @Column(name = "is_default", nullable = false)
    @Builder.Default
    private Boolean isDefault = false;

    @Column(name = "is_verified", nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @Column(name = "verification_date")
    private LocalDateTime verificationDate;

    @Column(name = "verification_method", length = 50)
    private String verificationMethod;

    @Column(name = "verification_reference", length = 100)
    private String verificationReference;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "document_image_path", length = 500)
    private String documentImagePath;

    @Column(name = "ocr_extracted_data", columnDefinition = "TEXT")
    private String ocrExtractedData;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "blacklist_check_status", length = 20)
    private String blacklistCheckStatus;

    @Column(name = "blacklist_check_date")
    private LocalDateTime blacklistCheckDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "created_by", length = 100, updatable = false)
    private String createdBy;

    @Column(name = "last_modified_by", length = 100)
    private String lastModifiedBy;

    // Constructor with required fields
    public Identification(Customer customer, String identificationTypeId, 
                         String identificationNumber, LocalDate issuedDate,
                         String issuingAuthority, String issuingCountryId) {
        this.customer = customer;
        this.identificationTypeId = identificationTypeId;
        this.identificationNumber = identificationNumber;
        this.issuedDate = issuedDate;
        this.issuingAuthority = issuingAuthority;
        this.issuingCountryId = issuingCountryId;
        this.isDefault = false;
        this.isVerified = false;
        this.isActive = true;
    }

    // Business methods
    public void updateIdentification(String identificationNumber, LocalDate issuedDate,
                                   LocalDate expiryDate, String issuingAuthority,
                                   String issuingCountryId, String issuingProvinceId) {
        this.identificationNumber = identificationNumber;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.issuingAuthority = issuingAuthority;
        this.issuingCountryId = issuingCountryId;
        this.issuingProvinceId = issuingProvinceId;
    }

    public void verify(String verificationMethod, String verificationReference) {
        this.isVerified = true;
        this.verificationDate = LocalDateTime.now();
        this.verificationMethod = verificationMethod;
        this.verificationReference = verificationReference;
    }

    public void setAsDefault() {
        this.isDefault = true;
    }

    public void unsetDefault() {
        this.isDefault = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public boolean isExpiringSoon(int daysThreshold) {
        if (expiryDate == null) return false;
        return expiryDate.isBefore(LocalDate.now().plusDays(daysThreshold));
    }

    public void updateBlacklistCheck(String status) {
        this.blacklistCheckStatus = status;
        this.blacklistCheckDate = LocalDateTime.now();
    }

    public void updateRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }
}