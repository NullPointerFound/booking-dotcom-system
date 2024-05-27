package com.booking.system.payment.service.domain.core;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
public class Payment extends AbstractDomainEntity<PaymentId> {

    private LocalDateTime createdAt;
    private ReservationOrderId reservationOrderId;
    private CustomerId customerId;
    private PaymentStatus status;
    private Money totalPrice;

    public void initialize() {
        this.setId(PaymentId.newInstance());
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ReservationOrderId getReservationOrderId() {
        return this.reservationOrderId;
    }

    public CustomerId getCustomerId() {
        return this.customerId;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public Money getTotalPrice() {
        return this.totalPrice;
    }
}
