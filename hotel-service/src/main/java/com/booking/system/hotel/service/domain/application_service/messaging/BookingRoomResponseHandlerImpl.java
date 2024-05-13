package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.BookingRoomConfirmedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomFailedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomPendingEvent;
import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;
import com.booking.system.hotel.service.domain.ports.api.messaging.BookingRoomResponseHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomResponseHandlerImpl implements BookingRoomResponseHandler {

    @Override
    public void handle(BookingRoomResponseEvent event) {
        switch (event) {
            case final BookingRoomPendingEvent e -> {
                // TODO: Publish an Event to Customer Service and Payment Service
            }
            case final BookingRoomConfirmedEvent e -> {
                // TODO: Publish an Event to Customer Service and Notification service
            }
            case final BookingRoomFailedEvent e -> {
                // TODO: Publish an Event to Customer Service and Notification service
            }
            default -> throw new IllegalStateException(
                    "Failed on handling sub-type of BookingRoomResponseEvent: Unknown event"
            );
        }
    }
}
