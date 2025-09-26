package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Identification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Identification entity
 */
@Repository
public interface IdentificationRepository extends JpaRepository<Identification, String> {

    /**
     * Find identifications by customer ID
     */
    List<Identification> findByCustomerCustomerId(String customerId);

    /**
     * Find active identifications by customer ID
     */
    @Query("SELECT i FROM Identification i WHERE i.customer.customerId = :customerId AND i.isActive = true")
    List<Identification> findActiveIdentificationsByCustomerId(@Param("customerId") String customerId);

    /**
     * Find default identification by customer ID
     */
    @Query("SELECT i FROM Identification i WHERE i.customer.customerId = :customerId AND i.isDefault = true AND i.isActive = true")
    Optional<Identification> findDefaultIdentificationByCustomerId(@Param("customerId") String customerId);

    /**
     * Find identification by number
     */
    Optional<Identification> findByIdentificationNumber(String identificationNumber);

    /**
     * Find identification by number hash
     */
    Optional<Identification> findByIdentificationNumberHash(String identificationNumberHash);

    /**
     * Find identifications by type
     */
    List<Identification> findByIdentificationTypeId(String identificationTypeId);

    /**
     * Find identifications by customer and type
     */
    @Query("SELECT i FROM Identification i WHERE i.customer.customerId = :customerId AND i.identificationTypeId = :typeId")
    List<Identification> findByCustomerIdAndType(@Param("customerId") String customerId, @Param("typeId") String identificationTypeId);

    /**
     * Find verified identifications
     */
    @Query("SELECT i FROM Identification i WHERE i.isVerified = true")
    List<Identification> findVerifiedIdentifications();

    /**
     * Find unverified identifications
     */
    @Query("SELECT i FROM Identification i WHERE i.isVerified = false AND i.isActive = true")
    List<Identification> findUnverifiedIdentifications();

    /**
     * Find identifications by issuing country
     */
    Page<Identification> findByIssuingCountryId(String issuingCountryId, Pageable pageable);

    /**
     * Find identifications by issuing province
     */
    List<Identification> findByIssuingProvinceId(String issuingProvinceId);

    /**
     * Find expired identifications
     */
    @Query("SELECT i FROM Identification i WHERE i.expiryDate < CURRENT_DATE AND i.isActive = true")
    List<Identification> findExpiredIdentifications();

    /**
     * Find identifications expiring soon
     */
    @Query("SELECT i FROM Identification i WHERE i.expiryDate BETWEEN CURRENT_DATE AND :expiryDate AND i.isActive = true")
    List<Identification> findIdentificationsExpiringSoon(@Param("expiryDate") LocalDate expiryDate);

    /**
     * Find identifications by verification method
     */
    List<Identification> findByVerificationMethod(String verificationMethod);

    /**
     * Find identifications by risk score range
     */
    @Query("SELECT i FROM Identification i WHERE i.riskScore BETWEEN :minScore AND :maxScore")
    List<Identification> findByRiskScoreRange(@Param("minScore") Integer minScore, @Param("maxScore") Integer maxScore);

    /**
     * Find identifications by blacklist check status
     */
    List<Identification> findByBlacklistCheckStatus(String blacklistCheckStatus);

    /**
     * Count identifications by customer
     */
    @Query("SELECT COUNT(i) FROM Identification i WHERE i.customer.customerId = :customerId")
    long countByCustomerId(@Param("customerId") String customerId);

    /**
     * Check if identification number exists
     */
    boolean existsByIdentificationNumber(String identificationNumber);

    /**
     * Find identifications issued in date range
     */
    @Query("SELECT i FROM Identification i WHERE i.issuedDate BETWEEN :startDate AND :endDate")
    List<Identification> findByIssuedDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    /**
     * Find identifications by issuing authority containing text
     */
    @Query("SELECT i FROM Identification i WHERE UPPER(i.issuingAuthority) LIKE UPPER(CONCAT('%', :authority, '%'))")
    List<Identification> findByIssuingAuthorityContaining(@Param("authority") String issuingAuthority);
}