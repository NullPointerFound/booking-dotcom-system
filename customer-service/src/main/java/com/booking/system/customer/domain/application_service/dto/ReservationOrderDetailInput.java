package com.booking.system.customer.domain.application_service.dto;

public record ReservationOrderDetailInput(
        String customerId,
        String reservationOrderId
) {
}
