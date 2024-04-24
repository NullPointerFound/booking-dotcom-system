package com.booking.system.booking.service.domain.core.entity;

import com.booking.system.booking.service.domain.core.valueobject.BookingId;
import com.booking.system.booking.service.domain.core.valueobject.BookingRoomId;
import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BookingRoom extends AbstractDomainEntity<BookingRoomId> {

    private RoomId roomId;
    private BookingId bookingId;
    private Integer quantity;
    private Money price;


    public BookingRoom(
            final BookingRoomId id,
            final RoomId roomId,
            final BookingId bookingId,
            final Integer quantity,
            final Money price
    ) {
        super(id);
        this.roomId = roomId;
        this.bookingId = bookingId;
        this.quantity = quantity;
        this.price = price;
    }
}