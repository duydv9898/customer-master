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

@Entity
@Table(name = "province")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "country")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class Province {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "province_id", length = 36)
    @EqualsAndHashCode.Include
    private String provinceId;

    @Column(name = "province_code", length = 10, nullable = false)
    private String provinceCode;

    @Column(name = "province_name", length = 100, nullable = false)
    private String provinceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

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

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    @Builder.Default
    private List<District> districts = new ArrayList<>();
}