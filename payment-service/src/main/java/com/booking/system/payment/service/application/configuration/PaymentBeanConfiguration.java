package com.booking.system.payment.service.application.configuration;

import com.booking.system.payment.service.domain.application_service.mapper.PaymentUseCaseMapperImpl;
import com.booking.system.payment.service.domain.application_service.messaging.PaymentRequestedHandlerImpl;
import com.booking.system.payment.service.domain.application_service.usecase.PayOrderUseCaseMockImpl;
import com.booking.system.payment.service.domain.ports.api.mapper.PaymentUseCaseMapper;
import com.booking.system.payment.service.domain.ports.api.messaging.PaymentRequestedHandler;
import com.booking.system.payment.service.domain.ports.api.usecase.PayOrderUseCase;
import com.booking.system.payment.service.domain.ports.spi.messaging.publisher.PaymentResponsePublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentBeanConfiguration {

    @Bean
    public PaymentUseCaseMapper paymentUseCaseMapper() {
        return new PaymentUseCaseMapperImpl();
    }

    @Bean
    public PayOrderUseCase payOrderUseCase(
            final PaymentUseCaseMapper paymentUseCaseMapper,
            @Value("${app.payment.mock.failure-chance-percentage}") final Integer failureChancePercentage
    ) {
        return new PayOrderUseCaseMockImpl(
                paymentUseCaseMapper,
                failureChancePercentage
        );
    }

    @Bean
    public PaymentRequestedHandler paymentRequestedHandler(
            final PayOrderUseCase payOrderUseCase,
            final PaymentUseCaseMapper paymentUseCaseMapper,
            final PaymentResponsePublisher paymentResponsePublisher
    ) {
        return new PaymentRequestedHandlerImpl(
                payOrderUseCase,
                paymentUseCaseMapper,
                paymentResponsePublisher
        );
    }

}

