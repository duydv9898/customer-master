package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "residency_status",
        uniqueConstraints = @UniqueConstraint(name = "uk_residency_status_code", columnNames = "residency_status_code"),
        indexes = @Index(name = "idx_residency_status_code", columnList = "residency_status_code")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResidencyStatus {
    @Id
    @Column(name = "residency_status_id", nullable = false)
    private UUID residencyStatusId;

    @Column(name = "residency_status_code", length = 20, nullable = false, unique = true)
    private String residencyStatusCode;

    @Column(name = "residency_status_name", length = 100, nullable = false)
    private String residencyStatusName;

    @Column(name = "residency_status_local", length = 100)
    private String residencyStatusLocal;

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

    @OneToMany(mappedBy = "residencyStatus", fetch = FetchType.LAZY)
    private List<Identification> identifications;
}