package com.backbase.customer_master.infrastructure.persistence.specification;

import com.backbase.customer_master.domain.model.*;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Specifications for Customer entity với fetch joins để lấy đủ dữ liệu
 */
public class CustomerSpecifications {

    /**
     * Base specification với fetch joins cho các entity liên quan
     */
    public static Specification<Customer> withAllRelatedData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                // Tránh fetch join khi count query
                root.fetch("addresses", JoinType.LEFT);
                root.fetch("identifications", JoinType.LEFT);
                root.fetch("products", JoinType.LEFT);
                root.fetch("relationships", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    /**
     * Base specification với fetch joins cho dữ liệu cơ bản
     */
    public static Specification<Customer> withBasicRelatedData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("addresses", JoinType.LEFT);
                root.fetch("identifications", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    /**
     * Specification cho summary view (chỉ default address)
     */
    public static Specification<Customer> withSummaryData() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                // Fetch chỉ default address
                Fetch<Customer, Address> addressFetch = root.fetch("addresses", JoinType.LEFT);
                query.distinct(true);
            }
            return criteriaBuilder.conjunction();
        };
    }

    // Customer field specifications

    /**
     * Find customers by customer ID (CIF)
     */
    public static Specification<Customer> hasCustomerId(String customerId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(customerId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customerId"), customerId);
        };
    }

    /**
     * Find customers by customer type
     */
    public static Specification<Customer> hasCustomerType(String customerType) {
        return (root, query, criteriaBuilder) -> {
            if (customerType == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customerType"), customerType);
        };
    }

    /**
     * Find customers by full name (case-insensitive, partial match)
     */
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

    /**
     * Find customers by email hash (for encrypted search)
     */
    public static Specification<Customer> hasEmailHash(String emailHash) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(emailHash)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("emailHash"), emailHash);
        };
    }

    /**
     * Find customers by phone hash (for encrypted search)
     */
    public static Specification<Customer> hasPhoneHash(String phoneHash) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(phoneHash)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("phoneHash"), phoneHash);
        };
    }

    /**
     * Find customers by tax ID hash (for encrypted search)
     */
    public static Specification<Customer> hasTaxIdHash(String taxIdHash) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(taxIdHash)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("taxIdHash"), taxIdHash);
        };
    }

    /**
     * Find customers by date of birth
     */
    public static Specification<Customer> hasDateOfBirth(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) -> {
            if (dateOfBirth == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
        };
    }

    /**
     * Find customers by date of birth range
     */
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

    /**
     * Find customers by CIF status
     */
    public static Specification<Customer> hasCifStatus(String cifStatus) {
        return (root, query, criteriaBuilder) -> {
            if (cifStatus == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("cifStatus"), cifStatus);
        };
    }

    /**
     * Find customers by branch ID
     */
    public static Specification<Customer> hasBranchId(String branchId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(branchId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("branchId"), branchId);
        };
    }

    /**
     * Find customers by customer segment
     */
    public static Specification<Customer> hasCustomerSegment(String customerSegment) {
        return (root, query, criteriaBuilder) -> {
            if (customerSegment == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("customerSegment"), customerSegment);
        };
    }

    /**
     * Find customers by relationship manager ID
     */
    public static Specification<Customer> hasRelationshipManagerId(String relationshipManagerId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(relationshipManagerId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("relationshipManagerId"), relationshipManagerId);
        };
    }

    /**
     * Find customers by KYC status
     */
    public static Specification<Customer> hasKycStatus(String kycStatus) {
        return (root, query, criteriaBuilder) -> {
            if (kycStatus == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("kycStatus"), kycStatus);
        };
    }

    /**
     * Find customers by risk level
     */
    public static Specification<Customer> hasRiskLevel(String riskLevel) {
        return (root, query, criteriaBuilder) -> {
            if (riskLevel == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("riskLevel"), riskLevel);
        };
    }

    /**
     * Find PEP customers
     */
    public static Specification<Customer> isPep(Boolean isPep) {
        return (root, query, criteriaBuilder) -> {
            if (isPep == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isPep"), isPep);
        };
    }

    /**
     * Find customers on sanctions list
     */
    public static Specification<Customer> isSanctionsList(Boolean isSanctionsList) {
        return (root, query, criteriaBuilder) -> {
            if (isSanctionsList == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isSanctionsList"), isSanctionsList);
        };
    }

    /**
     * Find customers by gender
     */
    public static Specification<Customer> hasGenderId(String genderId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(genderId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("genderId"), genderId);
        };
    }

    /**
     * Find customers by marital status
     */
    public static Specification<Customer> hasMaritalStatusId(String maritalStatusId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(maritalStatusId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("maritalStatusId"), maritalStatusId);
        };
    }

    /**
     * Find customers by nationality
     */
    public static Specification<Customer> hasNationalityId(String nationalityId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(nationalityId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("nationalityId"), nationalityId);
        };
    }

    /**
     * Find customers by occupation
     */
    public static Specification<Customer> hasOccupationContaining(String occupation) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(occupation)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("occupation")),
                "%" + occupation.toLowerCase() + "%"
            );
        };
    }

    // Related entity specifications

    /**
     * Find customers by identification number
     */
    public static Specification<Customer> hasIdentificationNumber(String identificationNumber) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(identificationNumber)) {
                return criteriaBuilder.conjunction();
            }
            
            Join<Customer, Identification> identificationJoin = root.join("identifications", JoinType.INNER);
            return criteriaBuilder.and(
                criteriaBuilder.equal(identificationJoin.get("identificationNumber"), identificationNumber),
                criteriaBuilder.equal(identificationJoin.get("isActive"), true)
            );
        };
    }

    /**
     * Find customers by address details (province, district)
     */
    public static Specification<Customer> hasAddressInProvince(String provinceId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(provinceId)) {
                return criteriaBuilder.conjunction();
            }
            
            Join<Customer, Address> addressJoin = root.join("addresses", JoinType.INNER);
            return criteriaBuilder.and(
                criteriaBuilder.equal(addressJoin.get("provinceId"), provinceId),
                criteriaBuilder.equal(addressJoin.get("isActive"), true)
            );
        };
    }

    /**
     * Find customers by address district
     */
    public static Specification<Customer> hasAddressInDistrict(String districtId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(districtId)) {
                return criteriaBuilder.conjunction();
            }
            
            Join<Customer, Address> addressJoin = root.join("addresses", JoinType.INNER);
            return criteriaBuilder.and(
                criteriaBuilder.equal(addressJoin.get("districtId"), districtId),
                criteriaBuilder.equal(addressJoin.get("isActive"), true)
            );
        };
    }

    /**
     * Find customers with specific product type
     */
    public static Specification<Customer> hasProductType(String productType) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(productType)) {
                return criteriaBuilder.conjunction();
            }
            
            Join<Customer, CustomerProduct> productJoin = root.join("products", JoinType.INNER);
            return criteriaBuilder.and(
                criteriaBuilder.equal(productJoin.get("productType"), productType),
                criteriaBuilder.equal(productJoin.get("productStatus"), "ACTIVE")
            );
        };
    }

    // Date and time specifications

    /**
     * Find customers created between dates
     */
    public static Specification<Customer> createdBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), fromDate));
            }
            
            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), toDate));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Find customers modified after certain date
     */
    public static Specification<Customer> modifiedAfter(LocalDateTime afterDate) {
        return (root, query, criteriaBuilder) -> {
            if (afterDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("lastModifiedDate"), afterDate);
        };
    }

    // Status and compliance specifications

    /**
     * Find customers with active status
     */
    public static Specification<Customer> isActive() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("cifStatus"), "ACTIVE");
    }

    /**
     * Find customers with inactive or suspended status
     */
    public static Specification<Customer> isInactiveOrSuspended() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.or(
                criteriaBuilder.equal(root.get("cifStatus"), "INACTIVE"),
                criteriaBuilder.equal(root.get("cifStatus"), "SUSPENDED")
            );
    }

    /**
     * Find high-risk customers
     */
    public static Specification<Customer> isHighRisk() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.or(
                criteriaBuilder.equal(root.get("riskLevel"), "HIGH"),
                criteriaBuilder.equal(root.get("isPep"), true),
                criteriaBuilder.equal(root.get("isSanctionsList"), true)
            );
    }

    /**
     * Find customers with expired identifications
     */
    public static Specification<Customer> hasExpiredIdentification() {
        return (root, query, criteriaBuilder) -> {
            Join<Customer, Identification> identificationJoin = root.join("identifications", JoinType.INNER);
            return criteriaBuilder.and(
                criteriaBuilder.lessThan(identificationJoin.get("expiryDate"), LocalDate.now()),
                criteriaBuilder.equal(identificationJoin.get("isActive"), true)
            );
        };
    }

    /**
     * Find customers with identification expiring soon
     */
    public static Specification<Customer> hasIdentificationExpiringSoon(int daysAhead) {
        return (root, query, criteriaBuilder) -> {
            Join<Customer, Identification> identificationJoin = root.join("identifications", JoinType.INNER);
            LocalDate futureDate = LocalDate.now().plusDays(daysAhead);
            
            return criteriaBuilder.and(
                criteriaBuilder.between(identificationJoin.get("expiryDate"), LocalDate.now(), futureDate),
                criteriaBuilder.equal(identificationJoin.get("isActive"), true)
            );
        };
    }

    // Complex search specifications

    /**
     * Complex search combining multiple criteria
     */
    public static Specification<Customer> complexSearch(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(searchTerm)) {
                return criteriaBuilder.conjunction();
            }
            
            String likePattern = "%" + searchTerm.toLowerCase() + "%";
            
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("customerId")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("occupation")), likePattern)
            );
        };
    }

    /**
     * Find customers requiring KYC renewal
     */
    public static Specification<Customer> requiresKycRenewal(LocalDateTime cutoffDate) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("cifStatus"), "ACTIVE"),
                criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("lastModifiedDate")),
                    criteriaBuilder.lessThan(root.get("lastModifiedDate"), cutoffDate)
                )
            );
        };
    }

    /**
     * Exclude closed customers (default filter)
     */
    public static Specification<Customer> excludeClosed() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.notEqual(root.get("cifStatus"), "CLOSED");
    }

    /**
     * Filter by created by user
     */
    public static Specification<Customer> createdBy(String createdBy) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(createdBy)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("createdBy"), createdBy);
        };
    }

    /**
     * Filter by last modified by user
     */
    public static Specification<Customer> lastModifiedBy(String lastModifiedBy) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(lastModifiedBy)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("lastModifiedBy"), lastModifiedBy);
        };
    }
}