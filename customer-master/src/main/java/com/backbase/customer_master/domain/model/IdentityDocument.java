package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "identity_document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityDocument {
    @Id
    @Column(name = "document_id", nullable = false)
    private UUID documentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id", referencedColumnName = "identification_id", nullable = false)
    private Identification identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "doc_type", length = 20, nullable = false)
    private String docType;

    @Column(name = "asset_id", nullable = false)
    private UUID assetId;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

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
}