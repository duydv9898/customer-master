package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_type",
        uniqueConstraints = @UniqueConstraint(name = "uk_client_type_code", columnNames = "client_type_code"),
        indexes = @Index(name = "idx_client_type_code", columnList = "client_type_code")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientType {
    @Id
    @Column(name = "client_type_id", nullable = false)
    private UUID clientTypeId;

    @Column(name = "client_type_code", length = 20, nullable = false, unique = true)
    private String clientTypeCode;

    @Column(name = "client_type_name", length = 100, nullable = false)
    private String clientTypeName;

    @Column(name = "client_type_local", length = 100)
    private String clientTypeLocal;

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

    @OneToMany(mappedBy = "clientType", fetch = FetchType.LAZY)
    private List<Customer> customers;
}
