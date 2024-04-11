package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;

import java.util.UUID;

public class HotelId extends AbstractDomainEntityId<UUID> {

    private HotelId(final UUID value) {
        super(value);
    }

    public static HotelId newInstance() {
        return new HotelId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
