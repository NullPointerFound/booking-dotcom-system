package com.booking.system.hotel.service.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq.routing-key")
public record RoutingKeyProperties(
        String bookingRoomRequested,
        String bookingRoomConfirmation,
        String customerBookingUpdate,
        String paymentRequest,
        String paymentConfirmation
) {

}
