package com.booking.system.payment.service.domain.application_service.mapper;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderInput;
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
}
