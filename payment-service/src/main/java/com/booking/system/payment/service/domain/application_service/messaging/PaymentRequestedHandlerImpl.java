package com.booking.system.payment.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;
import com.booking.system.payment.service.domain.ports.api.mapper.PaymentUseCaseMapper;
import com.booking.system.payment.service.domain.ports.api.messaging.PaymentRequestedHandler;
import com.booking.system.payment.service.domain.ports.api.usecase.PayOrderUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentRequestedHandlerImpl implements PaymentRequestedHandler {

    private final PayOrderUseCase payOrderUseCase;
    private final PaymentUseCaseMapper mapper;

    public PaymentRequestedHandlerImpl(
            final PayOrderUseCase payOrderUseCase,
            final PaymentUseCaseMapper mapper
    ) {
        this.payOrderUseCase = payOrderUseCase;
        this.mapper = mapper;
    }

    @Override
    public void handle(final PaymentRequestedEvent event) {
        log.info(
                "Payment request received, initiating payment for reservationOrderId={}, customerId={}",
                event.getReservationOrderId(),
                event.getCustomerId()
        );

        final var input = this.mapper.paymentRequestedEventToPayOrderInput(event);

        this.payOrderUseCase.execute(input);

    }

}
