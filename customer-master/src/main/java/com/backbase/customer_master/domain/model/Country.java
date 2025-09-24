package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Country entity for geographic reference data
 */
@Entity
@Table(name = "country")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class Country {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "country_id", length = 36)
    @EqualsAndHashCode.Include
    private String countryId;

    @Column(name = "country_code", length = 10, nullable = false, unique = true)
    private String countryCode;

    @Column(name = "country_name", length = 100, nullable = false)
    private String countryName;

    @Column(name = "country_name_en", length = 100)
    private String countryNameEn;

    @Column(name = "region", length = 50)
    private String region;

    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "phone_code", length = 10)
    private String phoneCode;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Province> provinces = new ArrayList<>();
}