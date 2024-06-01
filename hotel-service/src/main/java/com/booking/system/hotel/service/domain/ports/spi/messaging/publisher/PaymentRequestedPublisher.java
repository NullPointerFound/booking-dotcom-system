package com.booking.system.hotel.service.domain.ports.spi.messaging.publisher;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedPublisher {

    void publish(PaymentRequestedEvent event);

}
