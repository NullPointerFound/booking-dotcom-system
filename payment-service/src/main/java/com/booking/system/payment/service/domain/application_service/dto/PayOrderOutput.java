package com.booking.system.payment.service.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.FailureMessages;
import com.booking.system.commons.domain.core.valueobject.PaymentStatus;
import com.booking.system.payment.service.domain.core.Payment;
import lombok.Builder;

@Builder
public record PayOrderOutput(
        Payment payment,
        PaymentStatus status,
        FailureMessages failureMessages
) {
}
