package com.booking.system.payment.service.domain.application_service.usecase;

import com.booking.system.commons.domain.core.valueobject.FailureMessages;
import com.booking.system.commons.domain.core.valueobject.PaymentStatus;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderInput;
import com.booking.system.payment.service.domain.application_service.dto.PayOrderOutput;
import com.booking.system.payment.service.domain.ports.api.mapper.PaymentUseCaseMapper;
import com.booking.system.payment.service.domain.ports.api.usecase.PayOrderUseCase;

import java.util.Random;

public class PayOrderUseCaseImpl implements PayOrderUseCase {

    private final PaymentUseCaseMapper paymentUseCaseMapper;

    private final Integer failureChancePercentage;

    public PayOrderUseCaseImpl(
            final PaymentUseCaseMapper paymentUseCaseMapper,
            final Integer failureChancePercentage
    ) {
        this.paymentUseCaseMapper = paymentUseCaseMapper;
        this.failureChancePercentage = failureChancePercentage;
    }

    @Override
    public PayOrderOutput execute(final PayOrderInput input) {

        final var failureMessages = FailureMessages.empty();
        final var payment = this.paymentUseCaseMapper.payOrderInputToPayment(input);
        payment.initialize();
        this.pay(failureMessages);

        if (failureMessages.isNotEmpty()) {
            return new PayOrderOutput(
                    payment,
                    PaymentStatus.FAILED,
                    failureMessages
            );
        }

        return new PayOrderOutput(
                payment,
                PaymentStatus.COMPLETED,
                failureMessages
        );
    }

    private void pay(final FailureMessages failureMessages) {

        if (this.failureChancePercentage >= 100) {
            failureMessages.add("Customer doesn't have enough credit for payment");
            return;
        }
        if (this.failureChancePercentage == 0) {
            return;
        }

        final var successfullyPaidChance = new Random().nextInt(0, 100);
        final var successfullyPaid = successfullyPaidChance > this.failureChancePercentage;

        if (successfullyPaid) return; // success

        failureMessages.add("Customer doesn't have enough credit for payment");
    }

}
