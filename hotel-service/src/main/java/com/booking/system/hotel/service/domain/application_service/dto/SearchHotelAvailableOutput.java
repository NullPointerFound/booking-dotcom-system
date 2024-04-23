package com.booking.system.hotel.service.domain.application_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchHotelAvailableOutput(
        String id,
        String name,
        String description,
        String category,
        String address,
        String state,
        String city,
        String country,
        List<SearchHotelAvailableRoomOutput> rooms
) {
}
