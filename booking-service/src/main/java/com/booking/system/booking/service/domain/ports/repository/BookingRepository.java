package com.booking.system.booking.service.domain.ports.repository;

import com.booking.system.booking.service.domain.core.entity.Booking;

public interface BookingRepository {

    void save(Booking booking);
}
