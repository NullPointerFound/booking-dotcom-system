package com.booking.system.hotel.service.domain.domain.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class RoomId extends AbstractDomainEntityId<UUID> {
    private RoomId(final UUID value) {
        super(value);
    }

    public static RoomId newInstance() {
        return new RoomId(UUID.randomUUID());
    }

    public static RoomId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
        return new RoomId(value);
    }

    public static RoomId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
        return new RoomId(UUID.fromString(rawValue));
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
