package com.booking.system.booking.service.domain.application_service.dto;

import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.commons.domain.core.valueobject.FailureMessages;

public record BookingRoomOutput(
        Booking booking,
        CustomerReservationStatus status,
        FailureMessages failureMessages
) {
}
