package com.booking.system.hotel.service.domain.ports.spi.messaging.publisher;

import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface BookingRoomStatusChangedPublisher {
    void publish(BookingRoomStatusUpdatedEvent event);
}
