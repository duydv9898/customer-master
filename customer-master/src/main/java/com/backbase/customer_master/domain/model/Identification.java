package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "identification",
        indexes = {
                @Index(name = "idx_identification_customer", columnList = "customer_id"),
                @Index(name = "idx_identification_number", columnList = "identification_number"),
                @Index(name = "idx_identification_kyc", columnList = "kyc_status")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customer", "identityDocuments"})
@EqualsAndHashCode(exclude = {"customer", "identityDocuments"})
public class Identification {

    @Id
    @Column(name = "identification_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID identificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "kyc_status", length = 20, nullable = false)
    private String kycStatus;

    @Column(name = "identification_type", length = 30)
    private String identificationType;

    @Column(name = "identification_number", length = 50)
    private String identificationNumber;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "issuing_place", length = 120)
    private String issuingPlace;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residency_status", referencedColumnName = "residency_status_code", nullable = false)
    private ResidencyStatus residencyStatus;

    @Column(name = "successful_verification_date")
    private LocalDate successfulVerificationDate;

    @Column(name = "id_update_count")
    private Integer idUpdateCount;

    @Column(name = "last_id_update_date")
    private LocalDate lastIdUpdateDate;

    @Column(name = "watchlist_status", length = 20)
    private String watchlistStatus;

    @Column(name = "risk_rating", length = 10)
    private String riskRating;

    @Column(name = "last_screening_date")
    private LocalDate lastScreeningDate;

    @Version
    @Column(name = "version_no", nullable = false)
    private Integer versionNo;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 50, nullable = false)
    private String updatedBy;

    @Column(name = "source_app", length = 50)
    private String sourceApp;

    @Column(name = "correlation_id", length = 50)
    private String correlationId;

    @OneToMany(mappedBy = "identification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<IdentityDocument> identityDocuments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (identificationId == null) {
            identificationId = UUID.randomUUID();
        }
        if (versionNo == null) {
            versionNo = 0;
        }
        if (idUpdateCount == null) {
            idUpdateCount = 0;
        }
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void addDocument(IdentityDocument document) {
        identityDocuments.add(document);
        document.setIdentification(this);
    }

    public void removeDocument(IdentityDocument document) {
        identityDocuments.remove(document);
        document.setIdentification(null);
    }
}