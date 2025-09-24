package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Address entity for customer addresses
 */
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "address_id", length = 36)
    @EqualsAndHashCode.Include
    private String addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "address_type", length = 50, nullable = false)
    private String addressType;

    @Column(name = "address_line_1", length = 200, nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2", length = 200)
    private String addressLine2;

    @Column(name = "ward_id", length = 36)
    private String wardId;

    @Column(name = "district_id", length = 36, nullable = false)
    private String districtId;

    @Column(name = "province_id", length = 36, nullable = false)
    private String provinceId;

    @Column(name = "country_id", length = 36, nullable = false)
    private String countryId;

    @Column(name = "postal_code_id", length = 36)
    private String postalCodeId;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "latitude", precision = 10, scale = 8)
    private Double latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private Double longitude;

    @Column(name = "is_default", nullable = false)
    @Builder.Default
    private Boolean isDefault = false;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

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
    public Address(Customer customer, String addressType, String addressLine1,
                   String wardId, String districtId, String provinceId, String countryId) {
        this.customer = customer;
        this.addressType = addressType;
        this.addressLine1 = addressLine1;
        this.wardId = wardId;
        this.districtId = districtId;
        this.provinceId = provinceId;
        this.countryId = countryId;
        this.validFrom = LocalDateTime.now();
        this.isDefault = false;
        this.isActive = true;
    }

    // Business methods
    public void updateAddress(String addressLine1, String addressLine2, 
                            String wardId, String districtId, String provinceId, 
                            String countryId, String postalCodeId) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.wardId = wardId;
        this.districtId = districtId;
        this.provinceId = provinceId;
        this.countryId = countryId;
        this.postalCodeId = postalCodeId;
    }

    public void setAsDefault() {
        this.isDefault = true;
    }

    public void unsetDefault() {
        this.isDefault = false;
    }

    public void activate() {
        this.isActive = true;
        this.validFrom = LocalDateTime.now();
        this.validTo = null;
    }

    public void deactivate() {
        this.isActive = false;
        this.validTo = LocalDateTime.now();
    }

    public boolean isCurrentlyValid() {
        LocalDateTime now = LocalDateTime.now();
        return isActive && 
               (validFrom == null || validFrom.isBefore(now) || validFrom.isEqual(now)) &&
               (validTo == null || validTo.isAfter(now));
    }

    @PrePersist
    public void prePersist() {
        if (validFrom == null) {
            validFrom = LocalDateTime.now();
        }
    }
}