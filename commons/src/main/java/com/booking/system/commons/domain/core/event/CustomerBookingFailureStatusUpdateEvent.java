package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public abstract sealed class CustomerBookingFailureStatusUpdateEvent extends CustomerBookingStatusUpdatedEvent permits CustomerBookingRejectedEvent {


    private final List<String> failureMessages;

    public CustomerBookingFailureStatusUpdateEvent(
            final String reservationOrderId,
            final String customerId,
            final CustomerReservationStatus status,
            final List<String> failureMessages
    ) {
        super(reservationOrderId, customerId, status);
        this.failureMessages = failureMessages;
    }
}
