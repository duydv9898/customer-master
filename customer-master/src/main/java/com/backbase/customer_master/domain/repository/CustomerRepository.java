package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {

    // Basic queries
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPrimaryPhone(String primaryPhone);
    Optional<Customer> findByTaxFileNo(String taxFileNo);

    // Status queries
    List<Customer> findByCifStatus(String cifStatus);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.cifStatus = :status")
    long countByStatus(@Param("status") String status);

    // Client type queries
    Page<Customer> findByClientType_ClientTypeCode(String clientTypeCode, Pageable pageable);

    // Category queries
    Page<Customer> findByCategory_CategoryCode(String categoryCode, Pageable pageable);

    // Segment queries
    Page<Customer> findByCustomerSegment_SegmentCode(String segmentCode, Pageable pageable);

    // Full name search
    @Query("SELECT c FROM Customer c WHERE UPPER(c.fullName) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Customer> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    // Date of birth queries
    @Query("SELECT c FROM Customer c WHERE c.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Customer> findByDateOfBirthBetween(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

    // Industry classification queries
    List<Customer> findByClassificationIndustry_IndustryCode(String industryCode);

    // Business classification queries
    List<Customer> findByClassificationBusiness_BusinessClassCode(String businessClassCode);

    // Sector classification queries
    List<Customer> findByClassificationSector_SectorCode(String sectorCode);

    // Occupation queries
    List<Customer> findByOccupation_OccupationCode(String occupationCode);

    @Query("SELECT c FROM Customer c WHERE c.jobTitle LIKE %:jobTitle%")
    List<Customer> findByJobTitleContaining(@Param("jobTitle") String jobTitle);

    // Income queries
    List<Customer> findByMonthlyIncome(String monthlyIncome);
    List<Customer> findByMainIncomeSource(String mainIncomeSource);

    // Language and contact preferences
    List<Customer> findByPreferredLanguage_LanguageCode(String languageCode);
    List<Customer> findByPreferredContactChannel_ContactChannelCode(String channelCode);

    // Registration channel queries
    List<Customer> findByRegistrationChannel(String registrationChannel);

    @Query("SELECT c FROM Customer c WHERE c.cifCreatedDate BETWEEN :startDate AND :endDate")
    List<Customer> findByCifCreatedDateBetween(@Param("startDate") LocalDate startDate,
                                               @Param("endDate") LocalDate endDate);

    // Tax queries
    @Query("SELECT c FROM Customer c WHERE c.taxable = :taxable")
    List<Customer> findByTaxable(@Param("taxable") String taxable);

    boolean existsByTaxFileNo(String taxFileNo);

    // Internal client queries
    @Query("SELECT c FROM Customer c WHERE c.internalClient = :internalClient")
    List<Customer> findByInternalClient(@Param("internalClient") String internalClient);

    // Account usage purpose
    List<Customer> findByAccountUsagePurpose(String accountUsagePurpose);

    // Customer classification
    List<Customer> findByCustomerClassification(String customerClassification);

    // Complex queries with joins
    @Query("SELECT DISTINCT c FROM Customer c " +
            "LEFT JOIN FETCH c.addresses " +
            "LEFT JOIN FETCH c.identifications " +
            "WHERE c.customerId = :customerId")
    Optional<Customer> findByIdWithDetails(@Param("customerId") UUID customerId);

    @Query("SELECT DISTINCT c FROM Customer c " +
            "LEFT JOIN FETCH c.addresses a " +
            "WHERE a.province.provinceCode = :provinceCode")
    List<Customer> findByProvinceCode(@Param("provinceCode") String provinceCode);

    @Query("SELECT DISTINCT c FROM Customer c " +
            "LEFT JOIN FETCH c.addresses a " +
            "WHERE a.district.districtCode = :districtCode")
    List<Customer> findByDistrictCode(@Param("districtCode") String districtCode);

    @Query("SELECT DISTINCT c FROM Customer c " +
            "JOIN c.identifications i " +
            "WHERE i.identificationNumber = :idNumber")
    Optional<Customer> findByIdentificationNumber(@Param("idNumber") String identificationNumber);

    // Existence checks
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Customer c WHERE c.email = :email OR c.primaryPhone = :phone")
    boolean existsByEmailOrPrimaryPhone(@Param("email") String email, @Param("phone") String phone);

    // Statistics queries
    @Query("SELECT c.cifStatus, COUNT(c) FROM Customer c GROUP BY c.cifStatus")
    List<Object[]> countByStatusGrouped();

    @Query("SELECT c.clientType.clientTypeCode, COUNT(c) FROM Customer c GROUP BY c.clientType.clientTypeCode")
    List<Object[]> countByClientTypeGrouped();

    @Query("SELECT c.customerSegment.segmentCode, COUNT(c) FROM Customer c " +
            "WHERE c.customerSegment IS NOT NULL GROUP BY c.customerSegment.segmentCode")
    List<Object[]> countBySegmentGrouped();

    // Source app tracking
    List<Customer> findBySourceApp(String sourceApp);

    @Query("SELECT c FROM Customer c WHERE c.correlationId = :correlationId")
    List<Customer> findByCorrelationId(@Param("correlationId") String correlationId);
}