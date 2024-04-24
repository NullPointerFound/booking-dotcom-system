package com.booking.system.booking.service.domain.core.exception;

import java.io.Serial;

public class BookingDomainException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2745315837877180308L;

    public BookingDomainException() {
    }

    public BookingDomainException(final String message) {
        super(message);
    }

    public BookingDomainException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
