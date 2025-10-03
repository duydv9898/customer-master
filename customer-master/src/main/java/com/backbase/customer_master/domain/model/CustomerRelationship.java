package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_relationship",
        indexes = {
                @Index(name = "idx_customer_relationship_customer", columnList = "customer_id"),
                @Index(name = "idx_customer_relationship_related", columnList = "related_customer_id"),
                @Index(name = "idx_customer_relationship_type", columnList = "relationship_type"),
                @Index(name = "idx_customer_relationship_branch", columnList = "managing_branch"),
                @Index(name = "idx_customer_relationship_status", columnList = "status"),
                @Index(name = "idx_customer_relationship_group", columnList = "group_id")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customer", "relatedCustomer", "managingBranch"})
@EqualsAndHashCode(exclude = {"customer", "relatedCustomer", "managingBranch"})
public class CustomerRelationship {
    @Id
    @Column(name = "relationship_id", nullable = false)
    private UUID relationshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_customer_id", referencedColumnName = "customer_id")
    private Customer relatedCustomer;

    @Column(name = "group_id")
    private UUID groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_type", referencedColumnName = "relationship_type_code")
    private RelationshipType relationshipType;

    @Column(name = "rm_id", length = 20)
    private String rmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managing_branch", referencedColumnName = "branch_code")
    private Branch managingBranch;

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