package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "address",
        indexes = {
                @Index(name = "idx_address_customer", columnList = "customer_id"),
                @Index(name = "idx_address_province", columnList = "province_code"),
                @Index(name = "idx_address_district", columnList = "district_code")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customer"})
@EqualsAndHashCode(exclude = {"customer"})
public class Address {

    @Id
    @Column(name = "address_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domicile_country", referencedColumnName = "country_code", nullable = false)
    private Country domicileCountry;

    @Column(name = "permanent_address", length = 255, nullable = false)
    private String permanentAddress;

    @Column(name = "contact_address", length = 255)
    private String contactAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_code", referencedColumnName = "province_code")
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", referencedColumnName = "district_code")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_code", referencedColumnName = "ward_code")
    private Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code", referencedColumnName = "postal_code")
    private PostalCode postalCode;

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

    @PrePersist
    protected void onCreate() {
        if (addressId == null) {
            addressId = UUID.randomUUID();
        }
        if (versionNo == null) {
            versionNo = 0;
        }
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}