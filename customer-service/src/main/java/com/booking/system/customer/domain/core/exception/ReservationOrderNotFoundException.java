package com.booking.system.customer.domain.core.exception;

import com.booking.system.commons.domain.message.ApplicationMessage;

import java.io.Serial;

public class ReservationOrderNotFoundException extends CustomerDomainException {
    @Serial
    private static final long serialVersionUID = -1641830298069605965L;

    public ReservationOrderNotFoundException() {
        super(ApplicationMessage.CUSTOMER_RESERVATION_ORDER_NOT_FOUND);
    }

    public ReservationOrderNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
