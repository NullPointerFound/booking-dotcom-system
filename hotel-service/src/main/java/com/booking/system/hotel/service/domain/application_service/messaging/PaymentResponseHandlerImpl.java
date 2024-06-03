package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.PaymentResponseEvent;
import com.booking.system.hotel.service.domain.ports.api.messaging.PaymentResponseHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentResponseHandlerImpl implements PaymentResponseHandler {

    @Override
    public void handle(PaymentResponseEvent event) {

        log.info("Event of Type {} received", event.getClass());

        // TODO: Missing the implementation
    }
}
