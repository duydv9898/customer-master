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
@Table(name = "district")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "province")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class District {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "district_id", length = 36)
    @EqualsAndHashCode.Include
    private String districtId;

    @Column(name = "district_code", length = 10, nullable = false)
    private String districtCode;

    @Column(name = "district_name", length = 100, nullable = false)
    private String districtName;

    @Column(name = "district_type", length = 50)
    private String districtType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

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

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Ward> wards = new ArrayList<>();
}