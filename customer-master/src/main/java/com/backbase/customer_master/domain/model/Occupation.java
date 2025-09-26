package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "occupation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Occupation {
    @Id
    @Column(name = "occupation_id", nullable = false)
    private UUID occupationId;

    @Column(name = "occupation_code", length = 20, nullable = false, unique = true)
    private String occupationCode;

    @Column(name = "occupation_name", length = 100, nullable = false)
    private String occupationName;

    @Column(name = "occupation_local", length = 100)
    private String occupationLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_code", referencedColumnName = "industry_code")
    private Industry industry;

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

    @OneToMany(mappedBy = "occupation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}