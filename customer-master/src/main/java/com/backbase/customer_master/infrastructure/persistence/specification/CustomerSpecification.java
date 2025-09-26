package com.backbase.customer_master.infrastructure.persistence.specification;

import com.backbase.customer_master.domain.model.Customer;
import com.backbase.customer_master.domain.model.Address;
import com.backbase.customer_master.domain.model.Identification;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Specifications for Customer entity queries
 */
public class CustomerSpecification {

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
            if (StringUtils.isBlank(customerType)) {
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
     * Find customers by first name (case-insensitive, partial match)
     */
    public static Specification<Customer> hasFirstNameContaining(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(firstName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")),
                "%" + firstName.toLowerCase() + "%"
            );
        };
    }

    /**
     * Find customers by last name (case-insensitive, partial match)
     */
    public static Specification<Customer> hasLastNameContaining(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(lastName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")),
                "%" + lastName.toLowerCase() + "%"
            );
        };
    }

    /**
     * Find customers by email (case-insensitive)
     */
    public static Specification<Customer> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(email)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("email")),
                email.toLowerCase()
            );
        };
    }

    /**
     * Find customers by phone number
     */
    public static Specification<Customer> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(phoneNumber)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
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
            if (StringUtils.isBlank(cifStatus)) {
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
            if (StringUtils.isBlank(customerSegment)) {
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
            if (StringUtils.isBlank(kycStatus)) {
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
            if (StringUtils.isBlank(riskLevel)) {
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
     * Find customers by identification number
     */
    public static Specification<Customer> hasIdentificationNumber(String identificationNumber) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(identificationNumber)) {
                return criteriaBuilder.conjunction();
            }
            
            Join<Customer, Identification> identificationJoin = root.join("identifications", JoinType.INNER);
            return criteriaBuilder.equal(identificationJoin.get("identificationNumber"), identificationNumber);
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

    /**
     * Find customers with active status
     */
    public static Specification<Customer> isActive() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("cifStatus"), "ACTIVE");
    }

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
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likePattern),
                criteriaBuilder.like(root.get("phoneNumber"), likePattern)
            );
        };
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
}