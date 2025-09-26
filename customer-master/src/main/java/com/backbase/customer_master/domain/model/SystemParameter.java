package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_parameter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "param_group", length = 50, nullable = false)
    private String paramGroup;

    @Column(name = "param_type", length = 50, nullable = false)
    private String paramType;

    @Column(name = "param_value", length = 100, nullable = false)
    private String paramValue;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by", length = 50, nullable = false)
    private String updatedBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}