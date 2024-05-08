package com.booking.system.booking.service.domain.ports.api.messaging;

import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedHandler {

    void handle(BookingRoomRequestedEvent event);

}
