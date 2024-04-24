package com.booking.system.booking.service.domain.application_service.dto;

import java.math.BigDecimal;

public record BookingRoomItemInput(
        String roomId,
        Integer quantity,
        BigDecimal price
) {
}
