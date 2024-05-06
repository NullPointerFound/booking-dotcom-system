package com.booking.system.hotel.service.domain.application_service.dto;

import lombok.Builder;

@Builder
public record BookRoomItemInput(
        String roomId,
        Integer roomQuantity
) {
}