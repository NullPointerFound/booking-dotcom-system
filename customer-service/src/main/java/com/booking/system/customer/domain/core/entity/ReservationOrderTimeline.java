package com.booking.system.customer.domain.core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.customer.domain.core.valueobject.ReservationOrderTimelineId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public class ReservationOrderTimeline extends AbstractDomainEntity<ReservationOrderTimelineId> {

    private CustomerReservationStatus status;
    private String reason;
    private Instant occurredAt;

    public ReservationOrderTimeline(
            final ReservationOrderTimelineId id,
            final CustomerReservationStatus status,
            final String reason,
            final Instant occurredAt
    ) {
        super(id);
        this.status = status;
        this.reason = reason;
        this.occurredAt = occurredAt;
    }

    public CustomerReservationStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}