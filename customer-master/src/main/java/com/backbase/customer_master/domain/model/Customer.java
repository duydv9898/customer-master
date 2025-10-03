package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Customer Entity - Core domain entity representing a customer
 * Contains all customer information and relationships
 */
@Entity
@Table(name = "customer",
        indexes = {
                @Index(name = "idx_customer_email", columnList = "email"),
                @Index(name = "idx_customer_phone", columnList = "primary_phone"),
                @Index(name = "idx_customer_status", columnList = "cif_status"),
                @Index(name = "idx_customer_client_type", columnList = "client_type"),
                @Index(name = "idx_customer_segment", columnList = "customer_segment_code"),
                @Index(name = "idx_customer_created", columnList = "created_at")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"addresses", "identifications", "relationships", "products"})
@EqualsAndHashCode(exclude = {"addresses", "identifications", "relationships", "products"})
public class Customer {

    // ============================================
    // PRIMARY KEY
    // ============================================
    @Id
    @Column(name = "customer_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID customerId;

    // ============================================
    // BASIC INFORMATION
    // ============================================
    @Column(name = "cif_status", length = 20, nullable = false)
    private String cifStatus;

    @Column(name = "full_name", length = 120, nullable = false)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "cif_created_date", nullable = false)
    private LocalDate cifCreatedDate;

    // ============================================
    // CONTACT INFORMATION
    // ============================================
    @Column(name = "primary_phone", length = 20, nullable = false)
    private String primaryPhone;

    @Column(name = "secondary_phone", length = 20)
    private String secondaryPhone;

    @Column(name = "email", length = 254)
    private String email;

    // ============================================
    // REFERENCE DATA RELATIONSHIPS
    // ============================================

    // Gender - REQUIRED
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "gender_code", nullable = false)
    private Gender gender;

    // Nationality - REQUIRED
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality", referencedColumnName = "country_code", nullable = false)
    private Country nationality;

    // Marital Status - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marital_status", referencedColumnName = "marital_status_code")
    private MaritalStatus maritalStatus;

    // Client Type - REQUIRED
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_type", referencedColumnName = "client_type_code", nullable = false)
    private ClientType clientType;

    // Category - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "category_code")
    private Category category;

    // Preferred Contact Channel - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_contact_channel", referencedColumnName = "contact_channel_code")
    private ContactChannel preferredContactChannel;

    // Occupation - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupation", referencedColumnName = "occupation_code")
    private Occupation occupation;

    // Classification Industry - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_industry", referencedColumnName = "industry_code")
    private Industry classificationIndustry;

    // Classification Business - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_business", referencedColumnName = "business_class_code")
    private BusinessClassification classificationBusiness;

    // Classification Sector - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_sector", referencedColumnName = "sector_code")
    private EconomicSector classificationSector;

    // Preferred Language - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_language", referencedColumnName = "language_code")
    private Language preferredLanguage;

    // Customer Segment - OPTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_segment_code", referencedColumnName = "segment_code")
    private CustomerSegment customerSegment;

    // ============================================
    // EMPLOYMENT & FINANCIAL INFORMATION
    // ============================================
    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @Column(name = "monthly_income", length = 20)
    private String monthlyIncome;

    @Column(name = "main_income_source", length = 30)
    private String mainIncomeSource;

    @Column(name = "account_usage_purpose", length = 50, nullable = false)
    private String accountUsagePurpose;

    // ============================================
    // TAX & REGULATORY INFORMATION
    // ============================================
    @Column(name = "internal_client", length = 1)
    private String internalClient;

    @Column(name = "tax_file_no", length = 20)
    private String taxFileNo;

    @Column(name = "taxable", length = 1)
    private String taxable;

    @Column(name = "registration_channel", length = 20, nullable = false)
    private String registrationChannel;

    // ============================================
    // ADDITIONAL INFORMATION
    // ============================================
    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "customer_classification", length = 30)
    private String customerClassification;

    // ============================================
    // AUDIT & VERSION CONTROL
    // ============================================
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

    // ============================================
    // ONE-TO-MANY RELATIONSHIPS
    // ============================================

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Identification> identifications = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<CustomerRelationship> relationships = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<CustomerProduct> products = new ArrayList<>();

    // ============================================
    // LIFECYCLE CALLBACKS
    // ============================================

    @PrePersist
    protected void onCreate() {
        if (customerId == null) {
            customerId = UUID.randomUUID();
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

    // ============================================
    // BUSINESS METHODS
    // ============================================

    /**
     * Update customer status with audit trail
     */
    public void updateStatus(String newStatus) {
        this.cifStatus = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Add address to customer
     */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    /**
     * Remove address from customer
     */
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomer(null);
    }

    /**
     * Add identification to customer
     */
    public void addIdentification(Identification identification) {
        identifications.add(identification);
        identification.setCustomer(this);
    }

    /**
     * Remove identification from customer
     */
    public void removeIdentification(Identification identification) {
        identifications.remove(identification);
        identification.setCustomer(null);
    }

    /**
     * Add product to customer
     */
    public void addProduct(CustomerProduct product) {
        products.add(product);
        product.setCustomer(this);
    }

    /**
     * Remove product from customer
     */
    public void removeProduct(CustomerProduct product) {
        products.remove(product);
        product.setCustomer(null);
    }

    /**
     * Add relationship to customer
     */
    public void addRelationship(CustomerRelationship relationship) {
        relationships.add(relationship);
        relationship.setCustomer(this);
    }

    /**
     * Remove relationship from customer
     */
    public void removeRelationship(CustomerRelationship relationship) {
        relationships.remove(relationship);
        relationship.setCustomer(null);
    }

    /**
     * Check if customer is active
     */
    public boolean isActive() {
        return "ACTIVE".equals(this.cifStatus);
    }

    /**
     * Check if customer is inactive
     */
    public boolean isInactive() {
        return "INACTIVE".equals(this.cifStatus);
    }

    /**
     * Check if customer is suspended
     */
    public boolean isSuspended() {
        return "SUSPENDED".equals(this.cifStatus);
    }

    /**
     * Check if customer is closed
     */
    public boolean isClosed() {
        return "CLOSED".equals(this.cifStatus);
    }

    /**
     * Check if customer is internal client
     */
    public boolean isInternalClient() {
        return "Y".equals(this.internalClient);
    }

    /**
     * Check if customer is taxable
     */
    public boolean isTaxable() {
        return "Y".equals(this.taxable);
    }
}