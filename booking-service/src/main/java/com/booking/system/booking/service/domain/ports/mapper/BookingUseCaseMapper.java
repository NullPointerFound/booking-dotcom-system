package com.booking.system.booking.service.domain.ports.mapper;

import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.booking.service.domain.core.entity.Booking;

public interface BookingUseCaseMapper {

    Booking bookingRoomInputToBooking(final BookingRoomInput input);
}
