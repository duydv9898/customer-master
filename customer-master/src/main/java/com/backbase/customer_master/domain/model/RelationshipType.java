package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "relationship_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipType {
    @Id
    @Column(name = "relationship_type_id", nullable = false)
    private UUID relationshipTypeId;

    @Column(name = "relationship_type_code", length = 20, nullable = false, unique = true)
    private String relationshipTypeCode;

    @Column(name = "relationship_type_name", length = 100, nullable = false)
    private String relationshipTypeName;

    @Column(name = "relationship_type_local", length = 100)
    private String relationshipTypeLocal;

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

    @OneToMany(mappedBy = "relationshipType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerRelationship> customerRelationships;
}