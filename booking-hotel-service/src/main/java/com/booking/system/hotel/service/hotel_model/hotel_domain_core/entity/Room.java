package com.booking.system.hotel.service.hotel_model.hotel_domain_core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject.RoomId;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Room extends AbstractDomainEntity<RoomId> {

    private HotelId hotelId;
    private String name;
    private String description;
    private Integer capacity;
    private Money currentPrice;
    private Integer quantity;

    public Room(
            final RoomId id,
            final HotelId hotelId,
            final String name,
            final String description,
            final Integer capacity,
            final Money currentPrice,
            final Integer quantity
    ) {
        super(id);
        this.hotelId = hotelId;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }
}