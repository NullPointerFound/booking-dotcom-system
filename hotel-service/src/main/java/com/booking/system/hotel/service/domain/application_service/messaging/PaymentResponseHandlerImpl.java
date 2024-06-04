package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.PaymentCompletedEvent;
import com.booking.system.commons.domain.core.event.PaymentFailedEvent;
import com.booking.system.commons.domain.core.event.PaymentResponseEvent;
import com.booking.system.hotel.service.domain.ports.api.messaging.PaymentResponseHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentResponseHandlerImpl implements PaymentResponseHandler {

    @Override
    public void handle(PaymentResponseEvent event) {

        //TODO: need to be completed
        switch (event) {
            case final PaymentCompletedEvent e -> {
                log.info(
                        "Payment completed, notifying customer service & booking service | reservationOrderId={}",
                        e.getReservationOrderId()
                );

            }
            case final PaymentFailedEvent e -> {
                log.info(
                        "Payment failed with error messages \"{}\". Notifying customer service & booking service | reservationOrderId={}",
                        String.join(", ", e.getFailureMessages()),
                        e.getReservationOrderId()
                );

            }
            default -> throw new IllegalStateException(
                    "Failed on handling sub-type of PaymentResponseEvent: Unknown event"
            );
        }
    }
}
