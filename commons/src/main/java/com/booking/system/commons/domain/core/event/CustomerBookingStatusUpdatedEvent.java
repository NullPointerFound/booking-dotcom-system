package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public abstract sealed class CustomerBookingStatusUpdatedEvent
        implements Event permits CustomerBookingFailureStatusUpdateEvent, CustomerBookingInitiatedEvent, CustomerBookingPaymentRequestedEvent, CustomerBookingCompletedEvent, CustomerBookingPaymentCompletedEvent {

    private final Instant createdAt = Instant.now();
    private final String reservationOrderId;
    private final String customerId;
    private final CustomerReservationStatus status;

    protected CustomerBookingStatusUpdatedEvent(
            final String reservationOrderId,
            final String customerId,
            final CustomerReservationStatus status
    ) {
        this.reservationOrderId = reservationOrderId;
        this.customerId = customerId;
        this.status = status;
    }
}
