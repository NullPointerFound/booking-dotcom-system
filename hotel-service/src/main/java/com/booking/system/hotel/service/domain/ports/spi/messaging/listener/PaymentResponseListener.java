package com.booking.system.hotel.service.domain.ports.spi.messaging.listener;

import com.booking.system.commons.domain.core.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponseListener {

    void listen(PaymentResponseEvent event);

}