package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private UUID countryId;

    @Column(name = "country_code", length = 2, nullable = false, unique = true)
    private String countryCode;

    @Column(name = "country_name", length = 100, nullable = false)
    private String countryName;

    @Column(name = "country_name_local", length = 100)
    private String countryNameLocal;

    @Column(name = "iso3_code", length = 3)
    private String iso3Code;

    @Column(name = "numeric_code", length = 3)
    private String numericCode;

    @Column(name = "dialing_code", length = 10)
    private String dialingCode;

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

    @OneToMany(mappedBy = "domicileCountry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "nationality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;

    @OneToMany(mappedBy = "domicileCountry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Branch> branches;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Province> provinces;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostalCode> postalCodes;
}