package com.booking.system.hotel.service.domain.ports.spi.messaging.listener;

import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;

@FunctionalInterface
public interface BookingRoomResponseListener {

    void listen(BookingRoomResponseEvent event);

}