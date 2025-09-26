package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "business_classification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessClassification {
    @Id
    @Column(name = "business_class_id", nullable = false)
    private UUID businessClassId;

    @Column(name = "business_class_code", length = 20, nullable = false, unique = true)
    private String businessClassCode;

    @Column(name = "business_class_name", length = 150, nullable = false)
    private String businessClassName;

    @Column(name = "business_class_local", length = 150)
    private String businessClassLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_code", referencedColumnName = "industry_code")
    private Industry industry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", referencedColumnName = "business_class_code")
    private BusinessClassification parentBusinessClassification;

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

    @OneToMany(mappedBy = "classificationBusiness", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}