package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Customer entity representing CIF (Customer Information File)
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"addresses", "identifications", "relationships", "products"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id", length = 36)
    @EqualsAndHashCode.Include
    private String customerId;

    @Column(name = "customer_type", length = 50, nullable = false)
    private String customerType;

    @Column(name = "full_name", length = 200, nullable = false)
    private String fullName;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth", length = 200)
    private String placeOfBirth;

    @Column(name = "gender_id", length = 36)
    private String genderId;

    @Column(name = "marital_status_id", length = 36)
    private String maritalStatusId;

    @Column(name = "nationality_id", length = 36)
    private String nationalityId;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "secondary_phone", length = 20)
    private String secondaryPhone;

    @Column(name = "occupation", length = 100)
    private String occupation;

    @Column(name = "education_level", length = 50)
    private String educationLevel;

    @Column(name = "income_range", length = 50)
    private String incomeRange;

    @Column(name = "cif_status", length = 20, nullable = false)
    @Builder.Default
    private String cifStatus = "ACTIVE";

    @Column(name = "risk_level", length = 20)
    private String riskLevel;

    @Column(name = "is_pep")
    @Builder.Default
    private Boolean isPep = false;

    @Column(name = "is_sanctions_list")
    @Builder.Default
    private Boolean isSanctionsList = false;

    @Column(name = "kyc_status", length = 20)
    private String kycStatus;

    @Column(name = "branch_id", length = 36, nullable = false)
    private String branchId;

    @Column(name = "customer_segment", length = 50)
    private String customerSegment;

    @Column(name = "relationship_manager_id", length = 36)
    private String relationshipManagerId;

    @Column(name = "tax_id", length = 50)
    private String taxId;

    @Column(name = "source_of_fund", length = 100)
    private String sourceOfFund;

    @Column(name = "referral_source", length = 100)
    private String referralSource;

    @Column(name = "language_preference", length = 10)
    @Builder.Default
    private String languagePreference = "vi";

    @Column(name = "communication_preference", length = 50)
    private String communicationPreference;

    @Column(name = "is_consent_marketing")
    @Builder.Default
    private Boolean isConsentMarketing = false;

    @Column(name = "consent_date")
    private LocalDateTime consentDate;

    @Column(name = "last_contact_date")
    private LocalDateTime lastContactDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Version
    @Column(name = "version_no")
    private Long versionNo;

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

    // Relationships
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Identification> identifications = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CustomerRelationship> relationships = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CustomerProduct> products = new ArrayList<>();

    // Constructor with required fields
    public Customer(String customerType, String fullName, String firstName, 
                   String lastName, LocalDate dateOfBirth, String genderId, 
                   String branchId) {
        this.customerId = generateCustomerId();
        this.customerType = customerType;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.genderId = genderId;
        this.branchId = branchId;
        this.cifStatus = "ACTIVE";
        this.isPep = false;
        this.isSanctionsList = false;
        this.languagePreference = "vi";
        this.isConsentMarketing = false;
        this.addresses = new ArrayList<>();
        this.identifications = new ArrayList<>();
        this.relationships = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    // Business methods
    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomer(null);
    }

    public void addIdentification(Identification identification) {
        identifications.add(identification);
        identification.setCustomer(this);
    }

    public void removeIdentification(Identification identification) {
        identifications.remove(identification);
        identification.setCustomer(null);
    }

    public void updateProfile(String fullName, String firstName, String middleName, 
                            String lastName, String placeOfBirth, String maritalStatusId,
                            String email, String phoneNumber) {
        this.fullName = fullName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
        this.maritalStatusId = maritalStatusId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateStatus(String cifStatus) {
        this.cifStatus = cifStatus;
    }

    public boolean isActive() {
        return "ACTIVE".equals(cifStatus);
    }

    /**
     * Generate customer ID (CIF) in format: CIF + timestamp + random
     */
    private String generateCustomerId() {
        return "CIF" + System.currentTimeMillis() + String.format("%04d", 
               (int)(Math.random() * 10000));
    }

    @PrePersist
    public void prePersist() {
        if (customerId == null) {
            customerId = generateCustomerId();
        }
    }
}