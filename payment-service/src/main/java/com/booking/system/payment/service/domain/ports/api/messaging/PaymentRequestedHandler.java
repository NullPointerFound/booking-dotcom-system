package com.booking.system.payment.service.domain.ports.api.messaging;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedHandler {

    void handle(PaymentRequestedEvent event);

}
