package com.booking.system.customer.domain.ports.spi.repository;

import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.core.entity.ReservationOrder;

public interface ReservationOrderRepository {

    void save(ReservationOrder reservationOrder);

    ReservationOrder findById(ReservationOrderId id);
    
}
