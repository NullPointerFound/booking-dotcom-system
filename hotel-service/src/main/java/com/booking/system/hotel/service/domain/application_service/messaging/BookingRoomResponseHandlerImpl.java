package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;
import com.booking.system.hotel.service.domain.ports.api.messaging.BookingRoomResponseHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomResponseHandlerImpl implements BookingRoomResponseHandler {

    @Override
    public void handle(BookingRoomResponseEvent event) {
        log.info("Booking room Response Event Received of type Pending ={}", event.getClass());
    }
}
