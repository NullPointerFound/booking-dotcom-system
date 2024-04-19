package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class HotelId extends AbstractDomainEntityId<UUID> {

    private HotelId(final UUID value) {
        super(value);
    }

    public static HotelId newInstance() {
        return new HotelId(UUID.randomUUID());
    }

    public static HotelId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_NOT_NULL);
        return new HotelId(UUID.fromString(rawValue));
    }

    public static HotelId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.HOTEL_NOT_NULL);
        return new HotelId(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
