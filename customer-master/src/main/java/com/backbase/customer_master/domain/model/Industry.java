package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "industry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Industry {
    @Id
    @Column(name = "industry_id", nullable = false)
    private UUID industryId;

    @Column(name = "industry_code", length = 10, nullable = false, unique = true)
    private String industryCode;

    @Column(name = "industry_name", length = 150, nullable = false)
    private String industryName;

    @Column(name = "industry_local", length = 150)
    private String industryLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", referencedColumnName = "industry_code")
    private Industry parentIndustry;

    @Column(name = "description", length = 255)
    private String description;

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

    @OneToMany(mappedBy = "classificationIndustry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;

    @OneToMany(mappedBy = "industry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Occupation> occupations;

    @OneToMany(mappedBy = "industry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusinessClassification> businessClassifications;

    @OneToMany(mappedBy = "industry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EconomicSector> economicSectors;
}