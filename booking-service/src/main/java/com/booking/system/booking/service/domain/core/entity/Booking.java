package com.booking.system.booking.service.domain.core.entity;


import com.booking.system.booking.service.domain.core.valueobject.BookingId;
import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.*;
import lombok.experimental.SuperBuilder;
import com.booking.system.booking.service.domain.core.exception.BookingDomainException;
import com.booking.system.commons.domain.message.ApplicationMessage;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

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

    public void initialize() {
        this.setId(BookingId.newInstance());
        this.status = BookingStatus.PENDING;
        this.bookingRooms.forEach(room -> room.initialize(this.getId()));
    }

    public void validate() {
        this.validateReservationOrderId();
        this.bookingPeriod.validate();
        this.validateCustomerId();
        this.validateTotalPrice();
        this.validateBookingRooms();
        this.validateStatus();
    }

    private void validateStatus() {
        if (this.status == null) {
            throw new BookingDomainException(ApplicationMessage.BOOKING_STATUS_NOT_NULL);
        }
    }

    private void validateBookingRooms() {
        this.bookingRooms.forEach(BookingRoom::validate);
    }

    private void validateTotalPrice() {
        final var totalItensPrice = this.bookingRooms.stream()
                .map(BookingRoom::getTotalPrice)
                .reduce(Money.ZERO, Money::add);
        if (totalItensPrice.isNotEqual(this.totalPrice)) {
            throw new BookingDomainException(ApplicationMessage.BOOKING_TOTAL_PRICE_INVALID);
        }
    }

    private void validateCustomerId() {
        if (this.customerId == null || this.customerId.empty()) {
            throw new BookingDomainException(ApplicationMessage.BOOKING_CUSTOMER_NOT_NULL);
        }
    }

    private void validateReservationOrderId() {
        if (this.reservationOrderId == null || this.reservationOrderId.empty()) {
            throw new BookingDomainException(ApplicationMessage.BOOKING_RESERVATION_ORDER_INVALID);
        }
    }

    public boolean isBookingPeriodContainedIn(final BookingPeriod period) {
        return this.bookingPeriod.isContainedIn(period);
    }

    public void changeStatusTo(final BookingStatus status) {
        Objects.requireNonNull(status, ApplicationMessage.BOOKING_STATUS_NOT_NULL);
        this.status = status;
    }

    public List<RoomId> getRoomsId() {
        return this.bookingRooms.stream()
                .map(BookingRoom::getRoomId)
                .toList();
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