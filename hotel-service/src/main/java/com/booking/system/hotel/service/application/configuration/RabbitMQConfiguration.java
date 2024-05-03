package com.booking.system.hotel.service.application.configuration;

import com.booking.system.hotel.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.hotel.service.application.configuration.properties.QueueProperties;
import com.booking.system.hotel.service.application.configuration.properties.RoutingKeyProperties;
import lombok.AllArgsConstructor;
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
    public Queue bookingRoomRequestedQueue() {
        return new Queue(this.queueProperties.bookingRoomRequested(), true);
    }

}