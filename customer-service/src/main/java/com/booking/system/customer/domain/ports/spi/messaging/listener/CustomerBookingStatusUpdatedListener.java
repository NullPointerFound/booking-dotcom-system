package com.booking.system.customer.domain.ports.spi.messaging.listener;


import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;

import java.util.List;

@FunctionalInterface
public interface CustomerBookingStatusUpdatedListener {

    void listen(CustomerBookingStatusUpdatedEvent event);

}