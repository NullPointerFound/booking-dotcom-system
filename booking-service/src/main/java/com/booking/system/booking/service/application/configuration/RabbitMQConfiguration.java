package com.booking.system.booking.service.application.configuration;

import com.booking.system.booking.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.booking.service.application.configuration.properties.QueueProperties;
import com.booking.system.booking.service.application.configuration.properties.RoutingKeyProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;



@Configuration
@AllArgsConstructor
public class RabbitMQConfiguration {

    private final RoutingKeyProperties routingKeyProperties;

    private final QueueProperties queueProperties;

    private final ExchangeProperties exchangeProperties;

    @Bean
    public Queue bookingRoomRequestedQueue() {
        return new Queue(this.queueProperties.bookingRoomRequested(), true);
    }

    @Bean
    public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
