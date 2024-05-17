package com.booking.system.customer.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TimelineItem(
        CustomerReservationStatus status,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime occurredAt,
        String failureReason
) {
}
