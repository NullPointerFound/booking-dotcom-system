package com.booking.system.payment.service.domain.application_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PayOrderInput(
        String bookingRoomId,
        String reservationOrderId,
        String customerId,
        BigDecimal totalPrice
) {
}