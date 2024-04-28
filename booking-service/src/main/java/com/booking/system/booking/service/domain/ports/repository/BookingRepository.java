package com.booking.system.booking.service.domain.ports.repository;

import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.BookingPeriod;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.commons.domain.core.valueobject.RoomId;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    void save(Booking booking);

    List<Booking> findBookingByRoomIdAndPeriod(RoomId roomId, BookingPeriod bookingPeriod);

    Optional<Booking> findBookingByReservationOrderId(ReservationOrderId reservationOrderId);
}
