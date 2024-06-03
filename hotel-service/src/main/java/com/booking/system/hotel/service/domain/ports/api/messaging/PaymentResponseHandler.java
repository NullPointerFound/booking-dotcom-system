package com.booking.system.hotel.service.domain.ports.api.messaging;

import com.booking.system.commons.domain.core.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponseHandler {

    void handle(PaymentResponseEvent event);

}
