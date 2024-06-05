package com.booking.system.booking.service.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.BookingStatus;

public record UpdateBookingStatusOutput(
        String reservationOrderId,
        String customerId,
        BookingStatus status
) {
}
