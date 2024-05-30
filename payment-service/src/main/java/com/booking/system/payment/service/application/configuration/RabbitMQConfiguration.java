package com.booking.system.payment.service.application.configuration;

import com.booking.system.payment.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.payment.service.application.configuration.properties.QueueProperties;
import com.booking.system.payment.service.application.configuration.properties.RoutingKeyProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfiguration {

    private final RoutingKeyProperties routingKeyProperties;

    private final QueueProperties queueProperties;

    private final ExchangeProperties exchangeProperties;

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(this.exchangeProperties.payment());
    }

    @Bean
    public Queue paymentRequestQueue() {
        return new Queue(this.queueProperties.paymentRequest(), true);
    }

    @Bean
    public Queue paymentConfirmationQueue() {
        return new Queue(this.queueProperties.paymentConfirmation(), true);
    }

}
