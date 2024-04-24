package com.booking.system.booking.service.domain.application_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookingRoomInput (
    String reservationOrderId,
    String customerId,
    BigDecimal price,
    Integer guests,
    LocalDate checkIn,
    LocalDate checkOut,
    List<BookingRoomItemInput> rooms
){}
