package com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto;

import java.util.List;

public record RegisterHotelOutput(
        String hotelId,
        List<String> roomsId
) {}
