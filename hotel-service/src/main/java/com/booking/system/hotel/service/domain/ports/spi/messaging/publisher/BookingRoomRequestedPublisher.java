package com.booking.system.hotel.service.domain.ports.spi.messaging.publisher;

import com.booking.system.hotel.service.domain.core.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedPublisher {
    void publish(BookingRoomRequestedEvent event);
}
