
package com.backbase.customer_master.domain.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "customer_id", length = 20)
    private UUID customerId;

    @Column(name = "cif_status", length = 20, nullable = false)
    private String cifStatus;

    @Column(name = "full_name", length = 120, nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "gender_code", nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality", referencedColumnName = "country_code", nullable = false)
    private Country nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marital_status", referencedColumnName = "marital_status_code")
    private MaritalStatus maritalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_type", referencedColumnName = "client_type_code", nullable = false)
    private ClientType clientType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "category_code")
    private Category category;

    @Column(name = "primary_phone", length = 20, nullable = false)
    private String primaryPhone;

    @Column(name = "secondary_phone", length = 20)
    private String secondaryPhone;

    @Column(name = "email", length = 254)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_contact_channel", referencedColumnName = "contact_channel_code")
    private ContactChannel preferredContactChannel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupation", referencedColumnName = "occupation_code")
    private Occupation occupation;

    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_industry", referencedColumnName = "industry_code")
    private Industry classificationIndustry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_business", referencedColumnName = "business_class_code")
    private BusinessClassification classificationBusiness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_sector", referencedColumnName = "sector_code")
    private EconomicSector classificationSector;

    @Column(name = "monthly_income", length = 20)
    private String monthlyIncome;

    @Column(name = "main_income_source", length = 30)
    private String mainIncomeSource;

    @Column(name = "account_usage_purpose", length = 50, nullable = false)
    private String accountUsagePurpose;

    @Column(name = "internal_client", length = 1)
    private String internalClient;

    @Column(name = "tax_file_no", length = 20)
    private String taxFileNo;

    @Column(name = "taxable", length = 1)
    private String taxable;

    @Column(name = "registration_channel", length = 20, nullable = false)
    private String registrationChannel;

    @Column(name = "cif_created_date", nullable = false)
    private LocalDate cifCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_language", referencedColumnName = "language_code")
    private Language preferredLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_segment_code", referencedColumnName = "segment_code")
    private CustomerSegment customerSegment;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "customer_classification", length = 30)
    private String customerClassification;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Identification> identifications;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerRelationship> relationships;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerProduct> products;
}