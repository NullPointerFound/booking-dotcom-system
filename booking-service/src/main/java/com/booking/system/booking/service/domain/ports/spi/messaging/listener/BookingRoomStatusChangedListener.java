package com.booking.system.booking.service.domain.ports.spi.messaging.listener;

import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface BookingRoomStatusChangedListener {

    void listen(BookingRoomStatusUpdatedEvent event);

}

