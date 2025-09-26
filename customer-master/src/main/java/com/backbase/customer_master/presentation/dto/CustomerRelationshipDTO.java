package com.backbase.customer_master.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Customer Relationship Data Transfer Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Customer relationship information")
public class CustomerRelationshipDTO {

    @Schema(description = "Relationship ID")
    private String relationshipId;

    @Schema(description = "Customer ID")
    private String customerId;

    @Schema(description = "Related customer ID")
    private String relatedCustomerId;

    @Schema(description = "Relationship type", example = "SPOUSE", allowableValues = {"SPOUSE", "PARENT", "CHILD", "SIBLING", "GUARDIAN", "BENEFICIARY", "PARTNER"})
    private String relationshipType;

    @Schema(description = "Relationship status", example = "ACTIVE", allowableValues = {"ACTIVE", "INACTIVE"})
    private String relationshipStatus;

    @Schema(description = "Start date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(description = "End date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Schema(description = "Is primary relationship", example = "true")
    private Boolean isPrimary;

    @Schema(description = "Additional notes")
    private String notes;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Schema(description = "Last modified date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Schema(description = "Created by user")
    private String createdBy;

    @Schema(description = "Last modified by user")
    private String lastModifiedBy;
}