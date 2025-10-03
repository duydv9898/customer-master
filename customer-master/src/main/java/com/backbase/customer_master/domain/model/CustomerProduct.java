package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_product",
        indexes = {
                @Index(name = "idx_customer_product_customer", columnList = "customer_id"),
                @Index(name = "idx_customer_product_group", columnList = "product_group")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customer"})
@EqualsAndHashCode(exclude = {"customer"})
public class CustomerProduct {

    @Id
    @Column(name = "customer_product_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID customerProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group", referencedColumnName = "product_group_code", nullable = false)
    private ProductGroup productGroup;

    @Column(name = "product_name", length = 120, nullable = false)
    private String productName;

    @Column(name = "product_id", length = 20, nullable = false)
    private String productId;

    @Column(name = "product_status", length = 20, nullable = false)
    private String productStatus;

    @Version
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

    @PrePersist
    protected void onCreate() {
        if (customerProductId == null) {
            customerProductId = UUID.randomUUID();
        }
        if (versionNo == null) {
            versionNo = 0;
        }
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}