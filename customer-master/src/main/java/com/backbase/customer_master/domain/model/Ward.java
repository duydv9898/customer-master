package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ward",
        uniqueConstraints = @UniqueConstraint(name = "uk_ward_code", columnNames = "ward_code"),
        indexes = {
                @Index(name = "idx_ward_code", columnList = "ward_code"),
                @Index(name = "idx_ward_district", columnList = "district_code"),
                @Index(name = "idx_ward_province", columnList = "province_code"),
                @Index(name = "idx_ward_country", columnList = "country_code")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"addresses", "branches", "postalCodes"})
@EqualsAndHashCode(exclude = {"addresses", "branches", "postalCodes"})
public class Ward {

    @Id
    @Column(name = "ward_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID wardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code", referencedColumnName = "country_code", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_code", referencedColumnName = "province_code", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", referencedColumnName = "district_code", nullable = false)
    private District district;

    @Column(name = "ward_code", length = 10, nullable = false, unique = true)
    private String wardCode;

    @Column(name = "ward_name", length = 120, nullable = false)
    private String wardName;

    @Column(name = "ward_name_local", length = 120)
    private String wardNameLocal;

    // ✅ FIXED: Tham chiếu đến postal_code_id (PRIMARY KEY) thay vì postal_code
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code_id", referencedColumnName = "postal_code_id")
    private PostalCode postalCode;

    @Column(name = "record_status", length = 20, nullable = false)
    private String recordStatus;

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

    @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Branch> branches = new ArrayList<>();

    @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
    @Builder.Default
    private List<PostalCode> postalCodes = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (wardId == null) {
            wardId = UUID.randomUUID();
        }
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (recordStatus == null) {
            recordStatus = "ACTIVE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}