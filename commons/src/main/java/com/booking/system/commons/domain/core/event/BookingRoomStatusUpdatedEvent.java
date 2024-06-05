package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
@AllArgsConstructor
public abstract sealed class BookingRoomStatusUpdatedEvent permits BookingRoomPaymentCompleted, BookingRoomPaymentFailed {
    private final Instant createdAt = Instant.now();
    private final String reservationOrderId;
    private final String customerId;
    private final BookingStatus status;
}
