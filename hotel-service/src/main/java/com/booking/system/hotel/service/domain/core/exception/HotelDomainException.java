package com.booking.system.hotel.service.domain.core.exception;


public final class HotelDomainException extends RuntimeException {

    public HotelDomainException(final String message) {
        super(message);
    }

    public HotelDomainException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
