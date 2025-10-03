package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_group",
        uniqueConstraints = @UniqueConstraint(name = "uk_product_group_code", columnNames = "product_group_code"),
        indexes = @Index(name = "idx_product_group_code", columnList = "product_group_code")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGroup {
    @Id
    @Column(name = "product_group_id", nullable = false)
    private UUID productGroupId;

    @Column(name = "product_group_code", length = 20, nullable = false, unique = true)
    private String productGroupCode;

    @Column(name = "product_group_name", length = 150, nullable = false)
    private String productGroupName;

    @Column(name = "product_group_local", length = 150)
    private String productGroupLocal;

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

    @OneToMany(mappedBy = "productGroup", fetch = FetchType.LAZY)
    private List<CustomerProduct> customerProducts;
}