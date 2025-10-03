package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contact_channel",
        uniqueConstraints = @UniqueConstraint(name = "uk_contact_channel_code", columnNames = "contact_channel_code"),
        indexes = @Index(name = "idx_contact_channel_code", columnList = "contact_channel_code")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactChannel {
    @Id
    @Column(name = "contact_channel_id", nullable = false)
    private UUID contactChannelId;

    @Column(name = "contact_channel_code", length = 20, nullable = false, unique = true)
    private String contactChannelCode;

    @Column(name = "contact_channel_name", length = 100, nullable = false)
    private String contactChannelName;

    @Column(name = "contact_channel_local", length = 100)
    private String contactChannelLocal;

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

    @OneToMany(mappedBy = "preferredContactChannel", fetch = FetchType.LAZY)
    private List<Customer> customers;
}