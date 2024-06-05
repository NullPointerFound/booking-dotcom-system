package com.booking.system.booking.service.domain.ports.api.messaging;

import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface BookingRoomStatusChangedHandler {

    void handle(BookingRoomStatusUpdatedEvent event);

}
