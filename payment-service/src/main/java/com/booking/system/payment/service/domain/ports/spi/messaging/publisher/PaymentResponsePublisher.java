package com.booking.system.payment.service.domain.ports.spi.messaging.publisher;

import com.booking.system.commons.domain.core.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponsePublisher {

    void publish(PaymentResponseEvent event);

}
