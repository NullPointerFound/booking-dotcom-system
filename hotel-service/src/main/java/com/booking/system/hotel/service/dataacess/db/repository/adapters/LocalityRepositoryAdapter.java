package com.booking.system.hotel.service.dataacess.db.repository.adapters;

import com.booking.system.hotel.service.dataacess.db.repository.LocalityJpaRepository;
import com.booking.system.hotel.service.domain.core.valueobject.LocalityId;
import com.booking.system.hotel.service.domain.ports.repository.LocalityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocalityRepositoryAdapter implements LocalityRepository {

    private final LocalityJpaRepository localityJpaRepository;

    @Override
    public boolean existsLocalityById(final LocalityId localityId) {
        return this.localityJpaRepository.existsById(localityId.getValue());
    }
}
