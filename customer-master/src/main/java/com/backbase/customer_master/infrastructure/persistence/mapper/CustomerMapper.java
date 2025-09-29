package com.backbase.customer_master.infrastructure.persistence.mapper;

import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.presentation.dto.CustomerDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface CustomerMapper {

    // Full mapping with all relationships
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "genderCode", source = "gender.genderCode")
    @Mapping(target = "genderName", source = "gender.genderName")
    @Mapping(target = "nationalityCode", source = "nationality.countryCode")
    @Mapping(target = "nationalityName", source = "nationality.countryName")
    @Mapping(target = "maritalStatusCode", source = "maritalStatus.maritalStatusCode")
    @Mapping(target = "maritalStatusName", source = "maritalStatus.maritalStatusName")
    @Mapping(target = "clientTypeCode", source = "clientType.clientTypeCode")
    @Mapping(target = "clientTypeName", source = "clientType.clientTypeName")
    @Mapping(target = "categoryCode", source = "category.categoryCode")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "occupationCode", source = "occupation.occupationCode")
    @Mapping(target = "occupationName", source = "occupation.occupationName")
    @Mapping(target = "industryCode", source = "classificationIndustry.industryCode")
    @Mapping(target = "industryName", source = "classificationIndustry.industryName")
    @Mapping(target = "businessClassCode", source = "classificationBusiness.businessClassCode")
    @Mapping(target = "businessClassName", source = "classificationBusiness.businessClassName")
    @Mapping(target = "sectorCode", source = "classificationSector.sectorCode")
    @Mapping(target = "sectorName", source = "classificationSector.sectorName")
    @Mapping(target = "preferredLanguageCode", source = "preferredLanguage.languageCode")
    @Mapping(target = "preferredLanguageName", source = "preferredLanguage.languageName")
    @Mapping(target = "contactChannelCode", source = "preferredContactChannel.contactChannelCode")
    @Mapping(target = "contactChannelName", source = "preferredContactChannel.contactChannelName")
    @Mapping(target = "segmentCode", source = "customerSegment.segmentCode")
    @Mapping(target = "segmentName", source = "customerSegment.segmentName")
    @Mapping(target = "addressDTOs", source = "addresses")
    @Mapping(target = "identificationDTOs", source = "identifications")
    @Mapping(target = "productDTOs", source = "products")
    @Mapping(target = "relationshipDTOs", source = "relationships")
    CustomerDTO toDTO(Customer customer);

    // Basic mapping without relationships
    @Named("toBasicDTO")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "genderCode", source = "gender.genderCode")
    @Mapping(target = "genderName", source = "gender.genderName")
    @Mapping(target = "nationalityCode", source = "nationality.countryCode")
    @Mapping(target = "nationalityName", source = "nationality.countryName")
    @Mapping(target = "clientTypeCode", source = "clientType.clientTypeCode")
    @Mapping(target = "clientTypeName", source = "clientType.clientTypeName")
    @Mapping(target = "segmentCode", source = "customerSegment.segmentCode")
    @Mapping(target = "segmentName", source = "customerSegment.segmentName")
    @Mapping(target = "addressDTOs", ignore = true)
    @Mapping(target = "identificationDTOs", ignore = true)
    @Mapping(target = "productDTOs", ignore = true)
    @Mapping(target = "relationshipDTOs", ignore = true)
    CustomerDTO toBasicDTO(Customer customer);

    // Summary mapping for list views
    @Named("toSummaryDTO")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "primaryPhone", source = "primaryPhone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "cifStatus", source = "cifStatus")
    @Mapping(target = "clientTypeCode", source = "clientType.clientTypeCode")
    @Mapping(target = "clientTypeName", source = "clientType.clientTypeName")
    @Mapping(target = "segmentCode", source = "customerSegment.segmentCode")
    @Mapping(target = "segmentName", source = "customerSegment.segmentName")
    @Mapping(target = "cifCreatedDate", source = "cifCreatedDate")
    @Mapping(target = "addressDTOs", ignore = true)
    @Mapping(target = "identificationDTOs", ignore = true)
    @Mapping(target = "productDTOs", ignore = true)
    @Mapping(target = "relationshipDTOs", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "monthlyIncome", ignore = true)
    @Mapping(target = "mainIncomeSource", ignore = true)
    @Mapping(target = "accountUsagePurpose", ignore = true)
    @Mapping(target = "notes", ignore = true)
    CustomerDTO toSummaryDTO(Customer customer);

    // List mapping
    @IterableMapping(qualifiedByName = "toSummaryDTO")
    List<CustomerDTO> toDTOList(List<Customer> customers);

    // Reverse mapping
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "maritalStatus", ignore = true)
    @Mapping(target = "clientType", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "occupation", ignore = true)
    @Mapping(target = "classificationIndustry", ignore = true)
    @Mapping(target = "classificationBusiness", ignore = true)
    @Mapping(target = "classificationSector", ignore = true)
    @Mapping(target = "preferredLanguage", ignore = true)
    @Mapping(target = "preferredContactChannel", ignore = true)
    @Mapping(target = "customerSegment", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "versionNo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    // Update existing entity from DTO
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "maritalStatus", ignore = true)
    @Mapping(target = "clientType", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "occupation", ignore = true)
    @Mapping(target = "classificationIndustry", ignore = true)
    @Mapping(target = "classificationBusiness", ignore = true)
    @Mapping(target = "classificationSector", ignore = true)
    @Mapping(target = "preferredLanguage", ignore = true)
    @Mapping(target = "preferredContactChannel", ignore = true)
    @Mapping(target = "customerSegment", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "versionNo", ignore = true)
    void updateEntityFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);
}