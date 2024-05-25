package com.booking.system.customer.domain.application_service.messaging;


import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;
import com.booking.system.customer.domain.ports.api.messaging.CustomerBookingStatusUpdatedHandler;

public class CustomerBookingStatusUpdatedHandlerImpl implements CustomerBookingStatusUpdatedHandler {


    @Override
    public void handle(final CustomerBookingStatusUpdatedEvent event) {

        //TODO: Implementing a condition based on event received
    }
}
