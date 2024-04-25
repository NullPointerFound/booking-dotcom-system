package com.booking.system.booking.service.domain.core.entity;


import com.booking.system.booking.service.domain.core.valueobject.BookingId;
import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
public class Booking extends AbstractDomainEntity<BookingId> {

    private final ReservationOrderId reservationOrderId;
    private final CustomerId customerId;
    private final BookingPeriod bookingPeriod;
    private final Money totalPrice;
    private final List<BookingRoom> bookingRooms;
    private final Integer guests;
    private Instant createdAt;
    private Instant updatedAt;
    private BookingStatus status;

    public Booking(
            final BookingId id,
            final ReservationOrderId reservationOrderId,
            final CustomerId customerId,
            final BookingPeriod bookingPeriod,
            final Money totalPrice,
            final List<BookingRoom> bookingRooms,
            final Integer guests,
            final BookingStatus status
    ) {
        super(id);
        this.reservationOrderId = reservationOrderId;
        this.customerId = customerId;
        this.bookingPeriod = bookingPeriod;
        this.totalPrice = totalPrice;
        this.bookingRooms = bookingRooms;
        this.guests = guests;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }


    public ReservationOrderId getReservationOrderId() {
        return reservationOrderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public BookingPeriod getBookingPeriod() {
        return bookingPeriod;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public List<BookingRoom> getBookingRooms() {
        return bookingRooms;
    }

    public Integer getGuests() {
        return guests;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public BookingStatus getStatus() {
        return status;
    }
}