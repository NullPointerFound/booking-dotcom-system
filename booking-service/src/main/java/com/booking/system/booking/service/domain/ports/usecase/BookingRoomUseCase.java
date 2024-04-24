package com.booking.system.booking.service.domain.ports.usecase;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;


@FunctionalInterface
public interface BookingRoomUseCase {

    BookingRoomOutput execute(BookingRoomInput input);

}
