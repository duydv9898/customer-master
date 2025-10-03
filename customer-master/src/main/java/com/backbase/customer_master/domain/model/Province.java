package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "province",
        uniqueConstraints = @UniqueConstraint(name = "uk_province_code", columnNames = "province_code"),
        indexes = {
                @Index(name = "idx_province_code", columnList = "province_code"),
                @Index(name = "idx_province_country", columnList = "country_code")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {
    @Id
    @Column(name = "province_id", nullable = false)
    private UUID provinceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code", referencedColumnName = "country_code", nullable = false)
    private Country country;

    @Column(name = "province_code", length = 10, nullable = false, unique = true)
    private String provinceCode;

    @Column(name = "province_name", length = 120, nullable = false)
    private String provinceName;

    @Column(name = "province_name_local", length = 120)
    private String provinceNameLocal;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

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

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<District> districts;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<PostalCode> postalCodes;
}