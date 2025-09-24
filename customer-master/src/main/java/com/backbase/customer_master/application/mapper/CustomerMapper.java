package com.backbase.customer_master.application.mapper;


import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.presentation.dto.CustomerDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * MapStruct mapper for Customer entity and DTOs
 */
@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, IdentificationMapper.class},
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CustomerMapper {

    /**
     * Entity to Response DTO
     */
    @Mapping(target = "addresses", source = "addresses")
    @Mapping(target = "identifications", source = "identifications")
    CustomerDto.Response toResponse(Customer customer);

    /**
     * List of entities to list of response DTOs
     */
    List<CustomerDto.Response> toResponseList(List<Customer> customers);

    /**
     * Entity to Summary DTO
     */
    CustomerDto.Summary toSummary(Customer customer);

    /**
     * List of entities to list of summary DTOs
     */
    List<CustomerDto.Summary> toSummaryList(List<Customer> customers);

    /**
     * Create Request DTO to Entity
     */
    @Mapping(target = "customerId", ignore = true) // Will be generated
    @Mapping(target = "cifStatus", constant = "ACTIVE")
    @Mapping(target = "isPep", constant = "false")
    @Mapping(target = "isSanctionsList", constant = "false")
    @Mapping(target = "isConsentMarketing", source = "isConsentMarketing", defaultValue = "false")
    @Mapping(target = "languagePreference", source = "languagePreference", defaultValue = "vi")
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "versionNo", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "consentDate", ignore = true)
    @Mapping(target = "lastContactDate", ignore = true)
    @Mapping(target = "riskLevel", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    Customer toEntity(CustomerDto.CreateRequest request);

    /**
     * Update existing entity from Update Request DTO
     */
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "customerType", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "genderId", ignore = true)
    @Mapping(target = "nationalityId", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "cifStatus", ignore = true)
    @Mapping(target = "isPep", ignore = true)
    @Mapping(target = "isSanctionsList", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "consentDate", ignore = true)
    @Mapping(target = "lastContactDate", ignore = true)
    @Mapping(target = "riskLevel", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    void updateEntity(@MappingTarget Customer customer, CustomerDto.UpdateRequest request);

    /**
     * Page of entities to PageResponse DTO
     */
    @Mapping(target = "content", source = "content")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "first", source = "first")
    @Mapping(target = "last", source = "last")
    CustomerDto.PageResponse toPageResponse(Page<Customer> page);

    /**
     * Custom method to convert Page content
     */
    default CustomerDto.PageResponse mapPage(Page<Customer> page) {
        if (page == null) {
            return null;
        }
        
        return CustomerDto.PageResponse.builder()
                .content(toResponseList(page.getContent()))
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .number(page.getNumber())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    /**
     * Post-processing method to handle business logic after mapping
     */
    @AfterMapping
    default void enrichCustomerResponse(@MappingTarget CustomerDto.Response response, Customer customer) {
        // Add any business logic here if needed
        // For example, calculate derived fields, format data, etc.
    }

    /**
     * Pre-processing method to handle business logic before mapping
     */
    @BeforeMapping
    default void prepareCustomerEntity(CustomerDto.CreateRequest request, @MappingTarget Customer customer) {
        // Add any business logic here if needed
        // For example, validate data, set defaults, etc.
        if (request.getLanguagePreference() == null || request.getLanguagePreference().trim().isEmpty()) {
            request.setLanguagePreference("vi");
        }
        if (request.getIsConsentMarketing() == null) {
            request.setIsConsentMarketing(false);
        }
    }
}