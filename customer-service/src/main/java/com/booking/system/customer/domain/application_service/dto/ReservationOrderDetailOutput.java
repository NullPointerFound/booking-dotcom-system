package com.booking.system.customer.domain.application_service.dto;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ReservationOrderDetailOutput(
        String customerId,
        String customerName,
        String customerCpf,
        String reservationOrderId,
        String hotelId,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate checkIn,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate checkOut,
        BigDecimal totalPrice,
        CustomerReservationStatus status,
        List<TimelineItem> timeline
) {

}