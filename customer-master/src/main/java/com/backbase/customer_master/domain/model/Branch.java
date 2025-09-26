package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    @Id
    @Column(name = "branch_id", nullable = false)
    private UUID branchId;

    @Column(name = "branch_code", length = 10, nullable = false, unique = true)
    private String branchCode;

    @Column(name = "branch_name", length = 150, nullable = false)
    private String branchName;

    @Column(name = "branch_short_name", length = 50)
    private String branchShortName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domicile_country", referencedColumnName = "country_code", nullable = false)
    private Country domicileCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_code", referencedColumnName = "province_code", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", referencedColumnName = "district_code", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_code", referencedColumnName = "ward_code")
    private Ward ward;

    @Column(name = "address_line", length = 255, nullable = false)
    private String addressLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code", referencedColumnName = "postal_code")
    private PostalCode postalCode;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "record_status", length = 20, nullable = false)
    private String recordStatus;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

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