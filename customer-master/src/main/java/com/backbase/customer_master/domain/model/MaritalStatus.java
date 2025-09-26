package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "marital_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaritalStatus {
    @Id
    @Column(name = "marital_status_id", nullable = false)
    private UUID maritalStatusId;

    @Column(name = "marital_status_code", length = 10, nullable = false, unique = true)
    private String maritalStatusCode;

    @Column(name = "marital_status_name", length = 50, nullable = false)
    private String maritalStatusName;

    @Column(name = "marital_status_local", length = 50)
    private String maritalStatusLocal;

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

    @OneToMany(mappedBy = "maritalStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}