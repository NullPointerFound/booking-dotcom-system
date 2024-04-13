package com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RegisterHotelInput(
        String name,
        String description,
        String categoryId,
        String street,
        String cep,
        String localityId
) {
}
