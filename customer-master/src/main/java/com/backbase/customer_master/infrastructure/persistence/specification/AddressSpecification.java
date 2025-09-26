package com.backbase.customer_master.infrastructure.persistence.specification;

import com.backbase.customer_master.domain.model.Address;
import com.backbase.customer_master.domain.model.Customer;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddressSpecification {

    public static Specification<Address> belongsToCustomer(String customerId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(customerId)) {
                return criteriaBuilder.conjunction();
            }
            Join<Address, Customer> customerJoin = root.join("customer", JoinType.INNER);
            return criteriaBuilder.equal(customerJoin.get("customerId"), customerId);
        };
    }

    public static Specification<Address> hasAddressType(String addressType) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(addressType)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("addressType"), addressType);
        };
    }

    public static Specification<Address> inProvince(String provinceId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(provinceId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("provinceId"), provinceId);
        };
    }

    public static Specification<Address> inDistrict(String districtId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(districtId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("districtId"), districtId);
        };
    }

    public static Specification<Address> isActive() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("isActive"), true);
    }

    public static Specification<Address> isDefault() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("isDefault"), true);
    }

    public static Specification<Address> isCurrentlyValid() {
        return (root, query, criteriaBuilder) -> {
            LocalDateTime now = LocalDateTime.now();
            return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("isActive"), true),
                criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("validFrom")),
                    criteriaBuilder.lessThanOrEqualTo(root.get("validFrom"), now)
                ),
                criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("validTo")),
                    criteriaBuilder.greaterThan(root.get("validTo"), now)
                )
            );
        };
    }
}