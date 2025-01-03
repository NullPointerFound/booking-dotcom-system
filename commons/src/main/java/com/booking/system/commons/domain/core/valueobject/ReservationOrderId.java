package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;

import java.util.UUID;

public class ReservationOrderId extends AbstractDomainEntityId<UUID> {
    protected ReservationOrderId(final UUID value) {
        super(value);
    }

    public static ReservationOrderId newInstance() {
        return new ReservationOrderId(UUID.randomUUID());
    }

    public static ReservationOrderId of(final UUID value) {
        return new ReservationOrderId(value);
    }

    public static ReservationOrderId of(final String rawValue) {
        return new ReservationOrderId(UUID.fromString(rawValue));
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
