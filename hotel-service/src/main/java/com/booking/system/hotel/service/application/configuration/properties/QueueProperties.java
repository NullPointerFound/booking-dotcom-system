package com.booking.system.hotel.service.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq.queue")
public record QueueProperties(
        String bookingRoomRequested,
        String bookingRoomConfirmation,
        String bookingRoomStatusChanged,
        String paymentRequest,
        String paymentConfirmation,
        String customerBookingUpdate
) {

}