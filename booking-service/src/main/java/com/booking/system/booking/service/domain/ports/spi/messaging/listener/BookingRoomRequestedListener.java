package com.booking.system.booking.service.domain.ports.spi.messaging.listener;

import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedListener {

    void listen(BookingRoomRequestedEvent event);

}
