package com.booking.system.hotel.service.domain.application_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchHotelAvailableRoomOutput(
        String id,
        String name,
        String description,
        Integer capacity,
        BigDecimal currentPrice,
        Integer quantity
) {
}
