package com.booking.system.hotel.service.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;
import com.booking.system.commons.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class HotelCategoryId extends AbstractDomainEntityId<UUID> {

    private HotelCategoryId(final UUID value) {
        super(value);
    }

    public static HotelCategoryId of(final String rawValue) {
        Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_CATEGORY_NOT_NULL);
        return new HotelCategoryId(UUID.fromString(rawValue));
    }

    public static HotelCategoryId of(final UUID value) {
        Objects.requireNonNull(value, ApplicationMessage.HOTEL_CATEGORY_NOT_NULL);
        return new HotelCategoryId(value);
    }


    @Override
    public String toString() {
        return this.value.toString();
    }
}
