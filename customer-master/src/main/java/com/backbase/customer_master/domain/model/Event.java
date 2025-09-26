package com.backbase.customer_master.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @Column(name = "entity_name", length = 50, nullable = false)
    private String entityName;

    @Column(name = "entity_id", length = 100, nullable = false)
    private String entityId;

    @Column(name = "version_no", nullable = false)
    private Integer versionNo;

    @Column(name = "operation_type", length = 10, nullable = false)
    private String operationType;

    @Column(name = "changed_fields", columnDefinition = "JSON", nullable = false)
    private String changedFields;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;

    @Column(name = "event_by", length = 50, nullable = false)
    private String eventBy;

    @Column(name = "correlation_id", length = 50)
    private String correlationId;

    @Column(name = "source_app", length = 50)
    private String sourceApp;

    @Column(name = "remark", length = 255)
    private String remark;
}