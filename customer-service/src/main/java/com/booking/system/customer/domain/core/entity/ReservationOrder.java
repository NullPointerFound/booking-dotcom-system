package com.booking.system.customer.domain.core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.*;
import com.booking.system.commons.domain.message.ApplicationMessage;
import com.booking.system.customer.domain.core.exception.CustomerDomainException;
import com.booking.system.customer.domain.core.valueobject.Timeline;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;

@SuperBuilder
public class ReservationOrder extends AbstractDomainEntity<ReservationOrderId> {

    @Builder.Default
    private Timeline timeline = Timeline.empty();
    private CustomerId customerId;
    private HotelId hotelId;
    private Integer guests;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Money totalPrice;
    private CustomerReservationStatus currentStatus;

    protected ReservationOrder(final ReservationOrderId id) {
        super(id);
    }

    public void initialize() {
        if (Objects.isNull(this.getId())) {
            this.setId(ReservationOrderId.newInstance());
        }
        this.updateStatus(CustomerReservationStatus.AWAITING_RESERVATION);
    }


    public void updateStatus(final CustomerReservationStatus status) {
        this.currentStatus = status;
        this.timeline.add(ReservationOrderTimeline.update(status));
    }

    public void updateToFailureStatus(final CustomerReservationStatus status, FailureMessages failureMessages) {
        if (!CustomerReservationStatus.isFailureStatus(status)) {
            throw new CustomerDomainException(ApplicationMessage.CUSTOMER_RESERVATION_ORDER_STATUS_INVALID_STATE);
        }
        this.currentStatus = status;
        this.timeline.add(ReservationOrderTimeline.update(status, failureMessages));
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public HotelId getHotelId() {
        return hotelId;
    }

    public Integer getGuests() {
        return guests;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public CustomerReservationStatus getCurrentStatus() {
        return currentStatus;
    }
}