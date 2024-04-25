package com.booking.system.booking.service.domain.core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Room extends AbstractDomainEntity<RoomId> {

    private HotelId hotelId;
    private Integer capacity;
    private Integer quantity;

    public Room(
            final RoomId id,
            final HotelId hotelId,
            final Integer capacity,
            final Integer quantity
    ) {
        super(id);
        this.hotelId = hotelId;
        this.capacity = capacity;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCapacity() {
        return capacity;
    }
}