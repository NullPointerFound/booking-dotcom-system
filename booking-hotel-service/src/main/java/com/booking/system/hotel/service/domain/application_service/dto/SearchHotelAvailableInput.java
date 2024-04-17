package com.booking.system.hotel.service.domain.application_service.dto;

import lombok.Builder;

@Builder
public record SearchHotelAvailableInput(
        String city,
        String state,
        String category,
        String name
) {
}
