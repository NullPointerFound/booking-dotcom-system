package com.booking.system.booking.service.domain.application_service.messaging;


import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomStatusChangedHandlerImpl implements BookingRoomStatusChangedHandler {


    @Override
    public void handle(final BookingRoomStatusUpdatedEvent event) {
        log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);

        //TODO: Update BookingRoom Status
        //TODO: Publish BookingRoomConfirmedEvent

    }

}
