package com.booking.system.customer.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InitializeReservationOrderInput(
        String reservationOrderId,
        String customerId,
        String hotelId,
        BigDecimal totalPrice,
        Integer guests,
        LocalDate checkIn,
        LocalDate checkOut,
        CustomerReservationStatus status
) {


}
