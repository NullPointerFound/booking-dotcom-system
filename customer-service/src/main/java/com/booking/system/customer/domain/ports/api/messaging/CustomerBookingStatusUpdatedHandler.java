package com.booking.system.customer.domain.ports.api.messaging;


import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingStatusUpdatedHandler {

    void handle(CustomerBookingStatusUpdatedEvent event);

}
