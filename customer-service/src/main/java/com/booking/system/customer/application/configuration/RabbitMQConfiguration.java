package com.booking.system.customer.application.configuration;

import com.booking.system.customer.application.configuration.properties.ExchangeProperties;
import com.booking.system.customer.application.configuration.properties.QueueProperties;
import com.booking.system.customer.application.configuration.properties.RoutingKeyProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfiguration {

    private final RoutingKeyProperties routingKeyProperties;

    private final QueueProperties queueProperties;

    private final ExchangeProperties exchangeProperties;

    @Bean
    public DirectExchange customerBookingExchange() {
        return new DirectExchange(this.exchangeProperties.customerBooking());
    }

    @Bean
    public Queue customerBookingUpdateQueue() {
        return new Queue(this.queueProperties.customerBookingUpdate(), true);
    }

    @Bean
    public Binding customerBookingUpdateBinding(
            final DirectExchange customerBookingExchange,
            final Queue customerBookingUpdateQueue
    ) {
        return BindingBuilder.bind(customerBookingUpdateQueue)
                .to(customerBookingExchange)
                .with(this.routingKeyProperties.customerBookingUpdate());
    }

    @Bean
    public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}