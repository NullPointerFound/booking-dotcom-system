package com.booking.system.hotel.service.domain.ports.api.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomOutput;

@FunctionalInterface
public interface BookingRoomRequestUseCase {

    BookingRoomOutput execute(BookingRoomInput input);
}
