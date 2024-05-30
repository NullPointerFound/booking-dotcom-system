package com.booking.system.payment.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;
import com.booking.system.commons.domain.core.event.PaymentResponseEvent;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderOutput;
import com.booking.system.payment.service.domain.ports.api.mapper.PaymentUseCaseMapper;
import com.booking.system.payment.service.domain.ports.api.messaging.PaymentRequestedHandler;
import com.booking.system.payment.service.domain.ports.api.usecase.PayOrderUseCase;
import com.booking.system.payment.service.domain.ports.spi.messaging.publisher.PaymentResponsePublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentRequestedHandlerImpl implements PaymentRequestedHandler {

    private final PayOrderUseCase payOrderUseCase;
    private final PaymentUseCaseMapper mapper;
    private final PaymentResponsePublisher publisher;

    public PaymentRequestedHandlerImpl(
            final PayOrderUseCase payOrderUseCase,
            final PaymentUseCaseMapper mapper,
            final PaymentResponsePublisher publisher
    ) {
        this.payOrderUseCase = payOrderUseCase;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public void handle(final PaymentRequestedEvent event) {
        log.info(
                "Payment request received, initiating payment for reservationOrderId={}, customerId={}",
                event.getReservationOrderId(),
                event.getCustomerId()
        );

        final var input = this.mapper.paymentRequestedEventToPayOrderInput(event);

        final var output = this.payOrderUseCase.execute(input);

        final var paymentResponseEvent = this.mapOutputToEvent(output);

        this.publisher.publish(paymentResponseEvent);
    }

    private PaymentResponseEvent mapOutputToEvent(final PayOrderOutput output) {
        if (output.failureMessages().isNotEmpty()) {
            return this.mapper.payOrderOutputToPaymentFailedEvent(output);
        } else {
            return this.mapper.payOrderOutputToPaymentCompletedEvent(output);
        }
    }

}
