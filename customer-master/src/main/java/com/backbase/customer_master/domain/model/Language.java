package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {
    @Id
    @Column(name = "language_id", nullable = false)
    private UUID languageId;

    @Column(name = "language_code", length = 2, nullable = false, unique = true)
    private String languageCode;

    @Column(name = "language_name", length = 100, nullable = false)
    private String languageName;

    @Column(name = "language_local", length = 100)
    private String languageLocal;

    @Column(name = "iso_639_2_code", length = 3)
    private String iso6392Code;

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

    @OneToMany(mappedBy = "preferredLanguage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}