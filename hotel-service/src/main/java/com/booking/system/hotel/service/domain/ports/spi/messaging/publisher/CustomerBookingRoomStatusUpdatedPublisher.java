package com.booking.system.hotel.service.domain.ports.spi.messaging.publisher;

import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingRoomStatusUpdatedPublisher {

    void publish(CustomerBookingStatusUpdatedEvent event);

}
