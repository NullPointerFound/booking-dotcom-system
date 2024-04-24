package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class CustomerId extends AbstractDomainEntityId<UUID> {
    protected CustomerId(final UUID value) {
        super(value);
    }

    public static CustomerId newInstance() {
        return new CustomerId(UUID.randomUUID());
    }

    public static CustomerId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
        return new CustomerId(value);
    }

    public static CustomerId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
        return new CustomerId(UUID.fromString(rawValue));
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
