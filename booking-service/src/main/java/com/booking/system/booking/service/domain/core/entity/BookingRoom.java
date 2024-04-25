package com.booking.system.booking.service.domain.core.entity;

import com.booking.system.booking.service.domain.core.valueobject.BookingId;
import com.booking.system.booking.service.domain.core.valueobject.BookingRoomId;
import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class BookingRoom extends AbstractDomainEntity<BookingRoomId> {

    private RoomId roomId;
    private BookingId bookingId;
    private Integer quantity;
    private Money price;

    public void initialize(final BookingId bookingId) {
        this.setId(BookingRoomId.newInstance());
        this.bookingId = bookingId;
    }

    public void validate() {
        //TODO: need to be checked
    }

    public Money getTotalPrice() {
        return this.price.multiply(new BigDecimal(this.quantity));
    }


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

    public RoomId getRoomId() {
        return roomId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }
}