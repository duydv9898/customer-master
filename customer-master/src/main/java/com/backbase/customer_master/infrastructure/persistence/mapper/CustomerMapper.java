package com.backbase.customer_master.infrastructure.persistence.mapper;

import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MapStruct mapper for Customer entity
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface CustomerMapper {

    /**
     * Map Customer entity to DTO with all fields
     */
    @Mapping(target = "addressDTOs", source = "addresses")
    @Mapping(target = "identificationDTOs", source = "identifications")
    CustomerDTO toDTO(Customer customer);

    /**
     * Map Customer entity to basic DTO (without relationships)
     */
    @Mapping(target = "addressDTOs", ignore = true)
    @Mapping(target = "identificationDTOs", ignore = true)
    CustomerDTO toBasicDTO(Customer customer);

    /**
     * Map Customer entity to summary DTO (minimal fields for lists)
     */
    @Mapping(target = "addressDTOs", ignore = true)
    @Mapping(target = "identificationDTOs", ignore = true)
    @Mapping(target = "placeOfBirth", ignore = true)
    @Mapping(target = "secondaryPhone", ignore = true)
    @Mapping(target = "occupation", ignore = true)
    @Mapping(target = "educationLevel", ignore = true)
    @Mapping(target = "incomeRange", ignore = true)
    @Mapping(target = "sourceOfFund", ignore = true)
    @Mapping(target = "referralSource", ignore = true)
    @Mapping(target = "communicationPreference", ignore = true)
    @Mapping(target = "consentDate", ignore = true)
    @Mapping(target = "lastContactDate", ignore = true)
    @Mapping(target = "notes", ignore = true)
    CustomerDTO toSummaryDTO(Customer customer);

    /**
     * Map list of Customer entities to DTOs
     */
    List<CustomerDTO> toDTOList(List<Customer> customers);

    /**
     * Map CustomerDTO to Customer entity
     */
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "versionNo", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    /**
     * Update Customer entity from DTO
     */
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void updateEntityFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);
}