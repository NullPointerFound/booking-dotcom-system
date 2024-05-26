package com.booking.system.customer.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.commons.domain.core.valueobject.FailureMessages;

public record UpdateCustomerBookingFailureStatusInput(
        String customerId,
        String reservationOrderId,
        CustomerReservationStatus status,
        FailureMessages failureMessages
) {
}
