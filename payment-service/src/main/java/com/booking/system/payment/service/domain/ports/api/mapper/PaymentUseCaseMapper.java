package com.booking.system.payment.service.domain.ports.api.mapper;

import com.booking.system.commons.domain.core.event.PaymentRequestedEvent;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderInput;
import com.booking.system.payment.service.domain.core.Payment;

public interface PaymentUseCaseMapper {

    Payment payOrderInputToPayment(PayOrderInput input);

    PayOrderInput paymentRequestedEventToPayOrderInput(PaymentRequestedEvent event);


}
