package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.ContactChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Contact Channel Repository
@Repository
public interface ContactChannelRepository extends JpaRepository<ContactChannel, UUID> {
    Optional<ContactChannel> findByContactChannelCode(String contactChannelCode);
}
