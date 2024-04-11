package com.booking.system.hotel.service.hotel_model.hotel_domain_core.exception;


public final class HotelDomainException extends RuntimeException {

    public HotelDomainException(final String message) {
        super(message);
    }

    public HotelDomainException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
