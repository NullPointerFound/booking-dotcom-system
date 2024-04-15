package com.booking.system.hotel.service.domain.application_service.dto;

import java.util.List;

public record RegisterHotelOutput(
        String hotelId,
        List<String> roomsId
) {}
