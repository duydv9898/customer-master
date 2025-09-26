package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "gender")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gender {
    @Id
    @Column(name = "gender_id", nullable = false)
    private UUID genderId;

    @Column(name = "gender_code", length = 10, nullable = false, unique = true)
    private String genderCode;

    @Column(name = "gender_name", length = 50, nullable = false)
    private String genderName;

    @Column(name = "gender_name_local", length = 50)
    private String genderNameLocal;

    @Column(name = "iso_5218_code")
    private Integer iso5218Code;

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

    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}