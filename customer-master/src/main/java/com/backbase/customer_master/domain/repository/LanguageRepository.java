package com.backbase.customer_master.domain.repository;

import com.backbase.customer_master.domain.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Language Repository
@Repository
public interface LanguageRepository extends JpaRepository<Language, UUID> {
    Optional<Language> findByLanguageCode(String languageCode);
}
