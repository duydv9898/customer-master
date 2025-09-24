package com.backbase.customer_master.application.query.handler;

import com.backbase.customer_master.application.query.model.GetCustomerAddressesQuery;
import com.backbase.customer_master.presentation.dto.generated.model.AddressResponse;
import com.backbase.customer_master.domain.model.Address;
import com.backbase.customer_master.domain.repository.AddressRepository;
import com.backbase.customer_master.application.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for GetCustomerAddressesQuery
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetCustomerAddressesQueryHandler {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressResponse> handle(GetCustomerAddressesQuery query) {
        log.info("Handling GetCustomerAddressesQuery for customer ID: {}", query.getCustomerId());

        List<Address> addresses;

        if (query.getActiveOnly()) {
            addresses = addressRepository.findByCustomerCustomerIdAndIsActive(
                query.getCustomerId(), true
            );
        } else {
            addresses = addressRepository.findByCustomerCustomerId(query.getCustomerId());
        }

        // Filter by address type if specified
        if (query.getAddressType() != null && !query.getAddressType().isEmpty()) {
            addresses = addresses.stream()
                .filter(address -> query.getAddressType().equals(address.getAddressType()))
                .toList();
        }

        return addressMapper.toResponseList(addresses);
    }
}