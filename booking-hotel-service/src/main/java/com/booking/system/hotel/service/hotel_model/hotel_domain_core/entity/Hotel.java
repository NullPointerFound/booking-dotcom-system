package com.booking.system.hotel.service.hotel_model.hotel_domain_core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject.HotelAddress;
import com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject.LocalityId;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Hotel extends AbstractDomainEntity<HotelId> {

    private final String name;
    private final String description;
    private final HotelAddress address;
    private final HotelCategoryId categoryId;
    private final LocalityId localityId;
    private final Rooms rooms;

    public Hotel(
            final HotelId id,
            final String name,
            final String description,
            final HotelCategoryId categoryId,
            final HotelAddress address,
            final LocalityId localityId,
            final Rooms rooms
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.address = address;
        this.localityId = localityId;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HotelAddress getAddress() {
        return address;
    }

    public HotelCategoryId getCategoryId() {
        return categoryId;
    }

    public LocalityId getLocalityId() {
        return localityId;
    }

    public Rooms getRooms() {
        return rooms;
    }
}
