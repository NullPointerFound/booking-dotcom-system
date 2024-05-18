package com.booking.system.customer.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;

public record UpdateCustomerBookingStatusInput(
        String customerId,
        String reservationOrderId,
        CustomerReservationStatus status
) {
}
