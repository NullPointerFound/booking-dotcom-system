package com.booking.system.hotel.service.domain.domain.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.hotel.service.domain.domain.exception.HotelDomainException;
import com.booking.system.hotel.service.domain.domain.valueobject.RoomId;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.SuperBuilder;
import com.booking.system.commons.message.ApplicationMessage;


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
    public void validate() {
        this.validateNameAndDescription();
        this.validateCapacity();
        this.validateCurrentPrice();
        this.validateHotel();
        this.validateQuantity();
    }

    private void validateQuantity() {
        if (this.quantity == null) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_QUANTITY_NOT_NULL);
        }
        if (this.quantity <= 0) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_QUANTITY_INVALID);
        }
    }

    private void validateHotel() {
        if (this.hotelId == null || this.hotelId.empty()) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_RELATIONSHIP_NOT_FOUND);
        }
    }

    private void validateNameAndDescription() {
        if (StringUtils.isBlank(this.name)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NAME_NOT_NULL);
        }
        if (StringUtils.isBlank(this.description)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_DESCRIPTION_NOT_NULL);
        }
    }

    private void validateCapacity() {
        if (this.capacity == null) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_NOT_NULL);
        }
        if (this.capacity <= 0) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_INVALID);
        }
    }

    private void validateCurrentPrice() {
        if (this.currentPrice == null) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_NOT_NULL);
        }
        if (this.currentPrice.isNegative() || this.currentPrice.isZero()) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_INVALID);
        }
    }

    public void initialize(final HotelId hotelId) {
        this.setId(RoomId.newInstance());
        this.hotelId = hotelId;
    }
}