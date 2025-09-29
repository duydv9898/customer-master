package com.backbase.customer_master.infrastructure.persistence.specification;

import com.backbase.customer_master.domain.model.*;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerSpecifications {

    // Fetch joins specifications
    public static Specification<Customer> withAllRelatedData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("gender", JoinType.LEFT);
                root.fetch("nationality", JoinType.LEFT);
                root.fetch("maritalStatus", JoinType.LEFT);
                root.fetch("clientType", JoinType.LEFT);
                root.fetch("category", JoinType.LEFT);
                root.fetch("occupation", JoinType.LEFT);
                root.fetch("classificationIndustry", JoinType.LEFT);
                root.fetch("classificationBusiness", JoinType.LEFT);
                root.fetch("classificationSector", JoinType.LEFT);
                root.fetch("preferredLanguage", JoinType.LEFT);
                root.fetch("preferredContactChannel", JoinType.LEFT);
                root.fetch("customerSegment", JoinType.LEFT);
                root.fetch("addresses", JoinType.LEFT);
                root.fetch("identifications", JoinType.LEFT);
                root.fetch("products", JoinType.LEFT);
                root.fetch("relationships", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Customer> withBasicRelatedData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("gender", JoinType.LEFT);
                root.fetch("nationality", JoinType.LEFT);
                root.fetch("clientType", JoinType.LEFT);
                root.fetch("customerSegment", JoinType.LEFT);
                root.fetch("addresses", JoinType.LEFT);
                root.fetch("identifications", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Customer> withSummaryData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("clientType", JoinType.LEFT);
                root.fetch("customerSegment", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    // Basic field specifications
    public static Specification<Customer> hasCustomerId(UUID customerId) {
        return (root, query, criteriaBuilder) -> {
            if (customerId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customerId"), customerId);
        };
    }

    public static Specification<Customer> hasFullNameContaining(String fullName) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(fullName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("fullName")),
                    "%" + fullName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Customer> hasPrimaryPhone(String primaryPhone) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(primaryPhone)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("primaryPhone"), primaryPhone);
        };
    }

    public static Specification<Customer> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(email)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("email"), email);
        };
    }

    public static Specification<Customer> hasDateOfBirth(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) -> {
            if (dateOfBirth == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
        };
    }

    public static Specification<Customer> hasDateOfBirthBetween(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirth"), fromDate));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateOfBirth"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    // CIF Status specifications
    public static Specification<Customer> hasCifStatus(String cifStatus) {
        return (root, query, criteriaBuilder) -> {
            if (cifStatus == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("cifStatus"), cifStatus);
        };
    }

    public static Specification<Customer> isActive() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cifStatus"), "ACTIVE");
    }

    public static Specification<Customer> excludeClosed() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(root.get("cifStatus"), "CLOSED");
    }

    // Reference data specifications
    public static Specification<Customer> hasGenderCode(String genderCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(genderCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Gender> genderJoin = root.join("gender", JoinType.INNER);
            return criteriaBuilder.equal(genderJoin.get("genderCode"), genderCode);
        };
    }

    public static Specification<Customer> hasNationalityCode(String nationalityCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(nationalityCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Country> nationalityJoin = root.join("nationality", JoinType.INNER);
            return criteriaBuilder.equal(nationalityJoin.get("countryCode"), nationalityCode);
        };
    }

    public static Specification<Customer> hasMaritalStatusCode(String maritalStatusCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(maritalStatusCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, MaritalStatus> maritalJoin = root.join("maritalStatus", JoinType.INNER);
            return criteriaBuilder.equal(maritalJoin.get("maritalStatusCode"), maritalStatusCode);
        };
    }

    public static Specification<Customer> hasClientTypeCode(String clientTypeCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(clientTypeCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, ClientType> clientTypeJoin = root.join("clientType", JoinType.INNER);
            return criteriaBuilder.equal(clientTypeJoin.get("clientTypeCode"), clientTypeCode);
        };
    }

    public static Specification<Customer> hasCategoryCode(String categoryCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(categoryCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Category> categoryJoin = root.join("category", JoinType.INNER);
            return criteriaBuilder.equal(categoryJoin.get("categoryCode"), categoryCode);
        };
    }

    public static Specification<Customer> hasOccupationCode(String occupationCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(occupationCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Occupation> occupationJoin = root.join("occupation", JoinType.INNER);
            return criteriaBuilder.equal(occupationJoin.get("occupationCode"), occupationCode);
        };
    }

    public static Specification<Customer> hasIndustryCode(String industryCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(industryCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Industry> industryJoin = root.join("classificationIndustry", JoinType.INNER);
            return criteriaBuilder.equal(industryJoin.get("industryCode"), industryCode);
        };
    }

    public static Specification<Customer> hasBusinessClassCode(String businessClassCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(businessClassCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, BusinessClassification> businessJoin = root.join("classificationBusiness", JoinType.INNER);
            return criteriaBuilder.equal(businessJoin.get("businessClassCode"), businessClassCode);
        };
    }

    public static Specification<Customer> hasSectorCode(String sectorCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(sectorCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, EconomicSector> sectorJoin = root.join("classificationSector", JoinType.INNER);
            return criteriaBuilder.equal(sectorJoin.get("sectorCode"), sectorCode);
        };
    }

    public static Specification<Customer> hasLanguageCode(String languageCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(languageCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Language> languageJoin = root.join("preferredLanguage", JoinType.INNER);
            return criteriaBuilder.equal(languageJoin.get("languageCode"), languageCode);
        };
    }

    public static Specification<Customer> hasContactChannelCode(String contactChannelCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(contactChannelCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, ContactChannel> channelJoin = root.join("preferredContactChannel", JoinType.INNER);
            return criteriaBuilder.equal(channelJoin.get("contactChannelCode"), contactChannelCode);
        };
    }

    public static Specification<Customer> hasSegmentCode(String segmentCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(segmentCode)) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, CustomerSegment> segmentJoin = root.join("customerSegment", JoinType.INNER);
            return criteriaBuilder.equal(segmentJoin.get("segmentCode"), segmentCode);
        };
    }

    // Additional field specifications
    public static Specification<Customer> hasJobTitleContaining(String jobTitle) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(jobTitle)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("jobTitle")),
                    "%" + jobTitle.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Customer> hasMonthlyIncome(String monthlyIncome) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(monthlyIncome)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("monthlyIncome"), monthlyIncome);
        };
    }

    public static Specification<Customer> hasMainIncomeSource(String mainIncomeSource) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(mainIncomeSource)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("mainIncomeSource"), mainIncomeSource);
        };
    }

    public static Specification<Customer> hasAccountUsagePurpose(String accountUsagePurpose) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(accountUsagePurpose)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("accountUsagePurpose"), accountUsagePurpose);
        };
    }

    public static Specification<Customer> isInternalClient(String internalClient) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(internalClient)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("internalClient"), internalClient);
        };
    }

    public static Specification<Customer> hasTaxFileNo(String taxFileNo) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(taxFileNo)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("taxFileNo"), taxFileNo);
        };
    }

    public static Specification<Customer> isTaxable(String taxable) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(taxable)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("taxable"), taxable);
        };
    }

    public static Specification<Customer> hasRegistrationChannel(String registrationChannel) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(registrationChannel)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("registrationChannel"), registrationChannel);
        };
    }

    public static Specification<Customer> hasCifCreatedDateBetween(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("cifCreatedDate"), fromDate));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("cifCreatedDate"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Customer> hasCustomerClassification(String customerClassification) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(customerClassification)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customerClassification"), customerClassification);
        };
    }

    // Related entity specifications
    public static Specification<Customer> hasAddressInProvince(String provinceCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(provinceCode)) {
                return criteriaBuilder.conjunction();
            }

            Join<Customer, Address> addressJoin = root.join("addresses", JoinType.INNER);
            Join<Address, Province> provinceJoin = addressJoin.join("province", JoinType.INNER);
            return criteriaBuilder.equal(provinceJoin.get("provinceCode"), provinceCode);
        };
    }

    public static Specification<Customer> hasAddressInDistrict(String districtCode) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(districtCode)) {
                return criteriaBuilder.conjunction();
            }

            Join<Customer, Address> addressJoin = root.join("addresses", JoinType.INNER);
            Join<Address, District> districtJoin = addressJoin.join("district", JoinType.INNER);
            return criteriaBuilder.equal(districtJoin.get("districtCode"), districtCode);
        };
    }

    public static Specification<Customer> hasIdentificationNumber(String identificationNumber) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(identificationNumber)) {
                return criteriaBuilder.conjunction();
            }

            Join<Customer, Identification> identificationJoin = root.join("identifications", JoinType.INNER);
            return criteriaBuilder.equal(identificationJoin.get("identificationNumber"), identificationNumber);
        };
    }

    public static Specification<Customer> hasProductType(String productType) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(productType)) {
                return criteriaBuilder.conjunction();
            }

            Join<Customer, CustomerProduct> productJoin = root.join("products", JoinType.INNER);
            Join<CustomerProduct, ProductGroup> groupJoin = productJoin.join("productGroup", JoinType.INNER);
            return criteriaBuilder.equal(groupJoin.get("productGroupCode"), productType);
        };
    }

    // Date and time specifications
    public static Specification<Customer> createdBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Customer> modifiedAfter(LocalDateTime afterDate) {
        return (root, query, criteriaBuilder) -> {
            if (afterDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("updatedAt"), afterDate);
        };
    }

    // Source tracking specifications
    public static Specification<Customer> hasSourceApp(String sourceApp) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(sourceApp)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("sourceApp"), sourceApp);
        };
    }

    public static Specification<Customer> hasCorrelationId(String correlationId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(correlationId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("correlationId"), correlationId);
        };
    }

    public static Specification<Customer> createdBy(String createdBy) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(createdBy)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("createdBy"), createdBy);
        };
    }

    public static Specification<Customer> updatedBy(String updatedBy) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(updatedBy)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("updatedBy"), updatedBy);
        };
    }

    // Complex search
    public static Specification<Customer> complexSearch(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(searchTerm)) {
                return criteriaBuilder.conjunction();
            }

            String likePattern = "%" + searchTerm.toLowerCase() + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("primaryPhone")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("taxFileNo")), likePattern)
            );
        };
    }
}