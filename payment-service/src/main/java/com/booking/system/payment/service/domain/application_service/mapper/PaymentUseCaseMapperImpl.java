package com.booking.system.payment.service.domain.application_service.mapper;

import com.booking.system.commons.domain.core.event.PaymentCompletedEvent;
import com.booking.system.commons.domain.core.event.PaymentFailedEvent;
import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;
import com.booking.system.commons.domain.core.event.PaymentResponseEvent;
import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderInput;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderOutput;
import com.booking.system.payment.service.domain.core.Payment;
import com.booking.system.payment.service.domain.ports.api.mapper.PaymentUseCaseMapper;

public class PaymentUseCaseMapperImpl implements PaymentUseCaseMapper {

    @Override
    public Payment payOrderInputToPayment(PayOrderInput input) {
        return Payment.builder()
                .reservationOrderId(ReservationOrderId.of(input.reservationOrderId()))
                .customerId(CustomerId.of(input.customerId()))
                .totalPrice(Money.of(input.totalPrice()))
                .build();
    }

    @Override
    public PayOrderInput paymentRequestedEventToPayOrderInput(final PaymentRequestedEvent event) {
        return PayOrderInput.builder()
                .reservationOrderId(event.getReservationOrderId())
                .bookingRoomId(event.getBookingRoomId())
                .customerId(event.getCustomerId())
                .totalPrice(event.getTotalPrice())
                .build();
    }

    @Override
    public PaymentResponseEvent payOrderOutputToPaymentFailedEvent(final PayOrderOutput output) {
        return PaymentFailedEvent.builder()
                .reservationOrderId(output.payment().getReservationOrderId().toString())
                .customerId(output.payment().getCustomerId().toString())
                .totalPrice(output.payment().getTotalPrice().getValue())
                .failureMessages(output.failureMessages())
                .status(output.status())
                .build();
    }

    @Override
    public PaymentResponseEvent payOrderOutputToPaymentCompletedEvent(final PayOrderOutput output) {
        return PaymentCompletedEvent.builder()
                .reservationOrderId(output.payment().getReservationOrderId().toString())
                .customerId(output.payment().getCustomerId().toString())
                .totalPrice(output.payment().getTotalPrice().getValue())
                .status(output.status())
                .build();
    }
}
