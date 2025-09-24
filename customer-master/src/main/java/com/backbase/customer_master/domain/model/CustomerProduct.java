package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Customer product entity for managing customer-product relationships
 */
@Entity
@Table(name = "customer_product")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_product_id", length = 36)
    @EqualsAndHashCode.Include
    private String customerProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "product_id", length = 36, nullable = false)
    private String productId;

    @Column(name = "product_type", length = 50, nullable = false)
    private String productType;

    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "product_status", length = 20, nullable = false)
    @Builder.Default
    private String productStatus = "ACTIVE";

    @Column(name = "opening_date")
    private LocalDateTime openingDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "current_balance", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Column(name = "available_balance", precision = 19, scale = 2)
    private BigDecimal availableBalance;

    @Column(name = "credit_limit", precision = 19, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "interest_rate", precision = 8, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "currency", length = 3)
    @Builder.Default
    private String currency = "VND";

    @Column(name = "is_primary", nullable = false)
    @Builder.Default
    private Boolean isPrimary = false;

    @Column(name = "branch_id", length = 36)
    private String branchId;

    @Column(name = "relationship_manager_id", length = 36)
    private String relationshipManagerId;

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
    public CustomerProduct(Customer customer, String productId, String productType, String accountNumber) {
        this.customer = customer;
        this.productId = productId;
        this.productType = productType;
        this.accountNumber = accountNumber;
        this.productStatus = "ACTIVE";
        this.isPrimary = false;
        this.currency = "VND";
        this.openingDate = LocalDateTime.now();
    }

    // Business methods
    public void activate() {
        this.productStatus = "ACTIVE";
        this.openingDate = LocalDateTime.now();
        this.closingDate = null;
    }

    public void deactivate() {
        this.productStatus = "INACTIVE";
        this.closingDate = LocalDateTime.now();
    }

    public void close() {
        this.productStatus = "CLOSED";
        this.closingDate = LocalDateTime.now();
    }

    public void suspend() {
        this.productStatus = "SUSPENDED";
    }

    public void setAsPrimary() {
        this.isPrimary = true;
    }

    public void unsetPrimary() {
        this.isPrimary = false;
    }

    public boolean isActive() {
        return "ACTIVE".equals(productStatus);
    }

    public void updateBalance(BigDecimal currentBalance, BigDecimal availableBalance) {
        this.currentBalance = currentBalance;
        this.availableBalance = availableBalance;
    }

    @PrePersist
    public void prePersist() {
        if (openingDate == null) {
            openingDate = LocalDateTime.now();
        }
    }
}