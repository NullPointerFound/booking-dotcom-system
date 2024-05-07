package com.booking.system.booking.service.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq.exchange")
public record ExchangeProperties(
        String bookingRoom
) {
}
