package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Address entity
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    /**
     * Find addresses by customer ID
     */
    List<Address> findByCustomerCustomerId(String customerId);

    /**
     * Find active addresses by customer ID
     */
    @Query("SELECT a FROM Address a WHERE a.customer.customerId = :customerId AND a.isActive = true")
    List<Address> findActiveAddressesByCustomerId(@Param("customerId") String customerId);

    /**
     * Find default address by customer ID
     */
    @Query("SELECT a FROM Address a WHERE a.customer.customerId = :customerId AND a.isDefault = true AND a.isActive = true")
    Optional<Address> findDefaultAddressByCustomerId(@Param("customerId") String customerId);

    /**
     * Find addresses by type
     */
    List<Address> findByAddressType(String addressType);

    /**
     * Find addresses by customer and type
     */
    @Query("SELECT a FROM Address a WHERE a.customer.customerId = :customerId AND a.addressType = :type")
    List<Address> findByCustomerIdAndType(@Param("customerId") String customerId, @Param("type") String addressType);

    /**
     * Find addresses by province
     */
    Page<Address> findByProvinceId(String provinceId, Pageable pageable);

    /**
     * Find addresses by district
     */
    List<Address> findByDistrictId(String districtId);

    /**
     * Find addresses by ward
     */
    List<Address> findByWardId(String wardId);

    /**
     * Find addresses by country
     */
    Page<Address> findByCountryId(String countryId, Pageable pageable);

    /**
     * Find addresses by postal code
     */
    List<Address> findByPostalCode(String postalCode);

    /**
     * Count addresses by customer
     */
    @Query("SELECT COUNT(a) FROM Address a WHERE a.customer.customerId = :customerId")
    long countByCustomerId(@Param("customerId") String customerId);

    /**
     * Find addresses within geographic bounds
     */
    @Query("SELECT a FROM Address a WHERE a.latitude BETWEEN :minLat AND :maxLat AND a.longitude BETWEEN :minLng AND :maxLng")
    List<Address> findAddressesInBounds(@Param("minLat") Double minLatitude, 
                                      @Param("maxLat") Double maxLatitude,
                                      @Param("minLng") Double minLongitude, 
                                      @Param("maxLng") Double maxLongitude);

    /**
     * Check if customer has address of specific type
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Address a WHERE a.customer.customerId = :customerId AND a.addressType = :type AND a.isActive = true")
    boolean existsByCustomerIdAndType(@Param("customerId") String customerId, @Param("type") String addressType);

    /**
     * Find addresses by address line containing text
     */
    @Query("SELECT a FROM Address a WHERE UPPER(a.addressLine1) LIKE UPPER(CONCAT('%', :searchText, '%')) OR UPPER(a.addressLine2) LIKE UPPER(CONCAT('%', :searchText, '%'))")
    List<Address> findByAddressLineContaining(@Param("searchText") String searchText);
}