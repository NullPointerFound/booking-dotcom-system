package com.booking.system.booking.service.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.BookingStatus;
import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import lombok.Builder;

@Builder
public record UpdateBookingStatusInput(
        ReservationOrderId reservationOrderId,
        CustomerId customerId,
        BookingStatus status
) {
}
