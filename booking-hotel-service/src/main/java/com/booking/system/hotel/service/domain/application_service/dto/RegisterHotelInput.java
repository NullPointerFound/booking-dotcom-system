package com.booking.system.hotel.service.domain.application_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RegisterHotelInput(
        String name,
        String description,
        String categoryId,
        String street,
        String zip,
        String localityId,
        List<RegisterHotelRoomInput> rooms

) {
}
