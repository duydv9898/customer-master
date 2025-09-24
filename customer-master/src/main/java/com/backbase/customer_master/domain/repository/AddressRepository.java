package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Address;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for Address entity
 */
public interface AddressRepository {
    
    Address save(Address address);
    
    Optional<Address> findById(String addressId);
    
    List<Address> findByCustomerCustomerId(String customerId);
    
    List<Address> findByCustomerCustomerIdAndIsActive(String customerId, Boolean isActive);
    
    Optional<Address> findByCustomerCustomerIdAndIsDefault(String customerId, Boolean isDefault);
    
    List<Address> findByAddressType(String addressType);
    
    List<Address> findByProvinceId(String provinceId);
    
    List<Address> findByDistrictId(String districtId);
    
    List<Address> findByWardId(String wardId);
    
    void deleteById(String addressId);
    
    boolean existsById(String addressId);
}