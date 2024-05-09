package com.booking.system.booking.service.domain.ports.spi.messaging.publiser;

import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;

public interface BookingRoomResponsePublisher {

    void publish(BookingRoomResponseEvent event);

}
