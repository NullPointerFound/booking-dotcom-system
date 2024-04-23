package com.booking.system.hotel.service.domain.ports.repository;

import com.booking.system.hotel.service.domain.domain.valueobject.LocalityId;

public interface LocalityRepository {

    boolean existsLocalityById(LocalityId localityId);
}
