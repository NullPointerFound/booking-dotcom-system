package com.booking.system.booking.service.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.domain.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class BookingId extends AbstractDomainEntityId<UUID> {

    protected BookingId(final UUID value) {
        super(value);
    }

    public static BookingId newInstance() {
        return new BookingId(UUID.randomUUID());
    }

    public static BookingId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.BOOKING_NOT_NULL);
        return new BookingId(UUID.fromString(rawValue));
    }

    public static BookingId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.BOOKING_NOT_NULL);
        return new BookingId(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}