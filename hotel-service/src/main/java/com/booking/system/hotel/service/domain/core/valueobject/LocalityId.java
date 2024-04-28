package com.booking.system.hotel.service.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.domain.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class LocalityId extends AbstractDomainEntityId<UUID> {
    private LocalityId(final UUID value) {
        super(value);
    }

    public static LocalityId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_LOCALITY_NOT_NULL);
        return new LocalityId(UUID.fromString(rawValue));
    }

    public static LocalityId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.HOTEL_LOCALITY_NOT_NULL);
        return new LocalityId(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
