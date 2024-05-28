package com.booking.system.payment.service.domain.ports.spi.messaging.listener;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedListener {

    void listen(PaymentRequestedEvent event);

}
