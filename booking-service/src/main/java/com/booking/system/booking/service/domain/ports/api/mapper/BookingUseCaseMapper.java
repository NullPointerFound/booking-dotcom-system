package com.booking.system.booking.service.domain.ports.api.mapper;

import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.commons.domain.core.event.BookingRoomFailedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomPendingEvent;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;

public interface BookingUseCaseMapper {

    Booking bookingRoomInputToBooking(BookingRoomInput input);


    BookingRoomInput bookingRoomRequestedEventToBookingRoomInput(
            BookingRoomRequestedEvent event
    );

    BookingRoomFailedEvent bookingRoomOutputToBookingRoomFailedEvent(BookingRoomOutput output);

    BookingRoomPendingEvent bookingRoomOutputToBookingRoomResponseEvent(BookingRoomOutput output);

}
