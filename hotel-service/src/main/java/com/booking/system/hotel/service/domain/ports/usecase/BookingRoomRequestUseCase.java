package com.booking.system.hotel.service.domain.ports.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomOutput;

//TODO: implementation the interface
@FunctionalInterface
public interface BookingRoomRequestUseCase {
    BookingRoomOutput execute(BookingRoomInput input);
}
