package com.booking.system.hotel.service.domain.ports.repository;

import com.booking.system.hotel.service.domain.core.valueobject.LocalityId;

public interface LocalityRepository {

    boolean existsLocalityById(LocalityId localityId);
}
