package com.booking.system.payment.service.domain.ports.api.usecase;

import com.booking.system.payment.service.domain.application_service.dto.PayOrderInput;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderOutput;

@FunctionalInterface
public interface PayOrderUseCase {

    PayOrderOutput execute(PayOrderInput input);

}