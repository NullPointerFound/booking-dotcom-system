package com.booking.system.hotel.service.domain.ports.api.messaging;

import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;

@FunctionalInterface
public interface BookingRoomResponseHandler {

    void handle(BookingRoomResponseEvent event);

}
