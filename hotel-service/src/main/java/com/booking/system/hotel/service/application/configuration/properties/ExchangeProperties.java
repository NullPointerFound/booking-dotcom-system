package com.booking.system.hotel.service.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq.exchange")
public record ExchangeProperties(
        String bookingRoom,
        String customerBooking,
        String payment

) {

}
