package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_segment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSegment {
    @Id
    @Column(name = "customer_segment_id", nullable = false)
    private UUID customerSegmentId;

    @Column(name = "segment_code", length = 10, nullable = false)
    private String segmentCode;

    @Column(name = "segment_name", length = 100, nullable = false)
    private String segmentName;

    @Column(name = "segment_local", length = 100)
    private String segmentLocal;

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

    @OneToMany(mappedBy = "customerSegment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}