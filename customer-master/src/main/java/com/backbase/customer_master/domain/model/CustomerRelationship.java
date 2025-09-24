package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Customer relationship entity for managing customer relationships
 */
@Entity
@Table(name = "customer_relationship")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerRelationship {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "relationship_id", length = 36)
    @EqualsAndHashCode.Include
    private String relationshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "related_customer_id", length = 36, nullable = false)
    private String relatedCustomerId;

    @Column(name = "relationship_type", length = 50, nullable = false)
    private String relationshipType;

    @Column(name = "relationship_status", length = 20, nullable = false)
    @Builder.Default
    private String relationshipStatus = "ACTIVE";

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_primary", nullable = false)
    @Builder.Default
    private Boolean isPrimary = false;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "created_by", length = 100, updatable = false)
    private String createdBy;

    @Column(name = "last_modified_by", length = 100)
    private String lastModifiedBy;

    // Constructor with required fields
    public CustomerRelationship(Customer customer, String relatedCustomerId, String relationshipType) {
        this.customer = customer;
        this.relatedCustomerId = relatedCustomerId;
        this.relationshipType = relationshipType;
        this.relationshipStatus = "ACTIVE";
        this.isPrimary = false;
        this.startDate = LocalDateTime.now();
    }

    // Business methods
    public void activate() {
        this.relationshipStatus = "ACTIVE";
        this.startDate = LocalDateTime.now();
        this.endDate = null;
    }

    public void deactivate() {
        this.relationshipStatus = "INACTIVE";
        this.endDate = LocalDateTime.now();
    }

    public void setAsPrimary() {
        this.isPrimary = true;
    }

    public void unsetPrimary() {
        this.isPrimary = false;
    }

    public boolean isActive() {
        return "ACTIVE".equals(relationshipStatus);
    }

    @PrePersist
    public void prePersist() {
        if (startDate == null) {
            startDate = LocalDateTime.now();
        }
    }
}