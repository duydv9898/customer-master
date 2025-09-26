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

/**
 * Repository interface for Customer entity
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    /**
     * Find customer by email
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Find customer by phone number
     */
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    /**
     * Find customers by status
     */
    List<Customer> findByCifStatus(String cifStatus);

    /**
     * Find customers by branch
     */
    Page<Customer> findByBranchId(String branchId, Pageable pageable);

    /**
     * Find customers by customer type
     */
    Page<Customer> findByCustomerType(String customerType, Pageable pageable);

    /**
     * Find customers by full name containing (case insensitive)
     */
    @Query("SELECT c FROM Customer c WHERE UPPER(c.fullName) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Customer> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Find customers by date of birth range
     */
    @Query("SELECT c FROM Customer c WHERE c.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Customer> findByDateOfBirthBetween(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);

    /**
     * Find customers with identification document
     */
    @Query("SELECT DISTINCT c FROM Customer c JOIN c.identifications i WHERE i.identificationNumber = :idNumber")
    Optional<Customer> findByIdentificationNumber(@Param("idNumber") String identificationNumber);

    /**
     * Find customers by relationship manager
     */
    List<Customer> findByRelationshipManagerId(String relationshipManagerId);

    /**
     * Find PEP customers
     */
    @Query("SELECT c FROM Customer c WHERE c.isPep = true")
    List<Customer> findPepCustomers();

    /**
     * Find customers in sanctions list
     */
    @Query("SELECT c FROM Customer c WHERE c.isSanctionsList = true")
    List<Customer> findSanctionsListCustomers();

    /**
     * Count customers by status
     */
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.cifStatus = :status")
    long countByStatus(@Param("status") String status);

    /**
     * Find customers created in date range
     */
    @Query("SELECT c FROM Customer c WHERE DATE(c.createdDate) BETWEEN :startDate AND :endDate")
    List<Customer> findByCreatedDateBetween(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);

    /**
     * Check if customer exists by email or phone
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email OR c.phoneNumber = :phone")
    boolean existsByEmailOrPhoneNumber(@Param("email") String email, @Param("phone") String phoneNumber);

    /**
     * Find customers by KYC status
     */
    List<Customer> findByKycStatus(String kycStatus);

    /**
     * Find customers by risk level
     */
    List<Customer> findByRiskLevel(String riskLevel);

    /**
     * Find customers by segment
     */
    Page<Customer> findByCustomerSegment(String customerSegment, Pageable pageable);
}