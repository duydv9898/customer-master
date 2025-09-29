package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Customer Segment Repository
@Repository
public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, UUID> {
    Optional<CustomerSegment> findBySegmentCode(String segmentCode);
}
