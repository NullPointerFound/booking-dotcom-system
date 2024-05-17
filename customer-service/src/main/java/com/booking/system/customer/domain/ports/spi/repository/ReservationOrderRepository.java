package com.booking.system.customer.domain.ports.spi.repository;

import com.booking.system.customer.domain.core.entity.ReservationOrder;

public interface ReservationOrderRepository {
    void save(ReservationOrder reservationOrder);

}
