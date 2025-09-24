package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.GetCustomerIdentificationsQuery;
import com.backbase.customer_master.presentation.dto.generated.model.IdentificationResponse;
import com.backbase.customer_master.domain.model.Identification;
import com.backbase.customer_master.domain.repository.IdentificationRepository;
import com.backbase.customer_master.application.mapper.IdentificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for GetCustomerIdentificationsQuery
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetCustomerIdentificationsQueryHandler {

    private final IdentificationRepository identificationRepository;
    private final IdentificationMapper identificationMapper;

    public List<IdentificationResponse> handle(GetCustomerIdentificationsQuery query) {
        log.info("Handling GetCustomerIdentificationsQuery for customer ID: {}", query.getCustomerId());

        List<Identification> identifications;

        if (query.getActiveOnly()) {
            identifications = identificationRepository.findByCustomerCustomerIdAndIsActive(
                query.getCustomerId(), true
            );
        } else {
            identifications = identificationRepository.findByCustomerCustomerId(query.getCustomerId());
        }

        // Filter by identification type if specified
        if (query.getIdentificationTypeId() != null && !query.getIdentificationTypeId().isEmpty()) {
            identifications = identifications.stream()
                .filter(id -> query.getIdentificationTypeId().equals(id.getIdentificationTypeId()))
                .toList();
        }

        // Filter by verification status if specified
        if (query.getVerifiedOnly()) {
            identifications = identifications.stream()
                .filter(Identification::getIsVerified)
                .toList();
        }

        return identificationMapper.toResponseList(identifications);
    }
}