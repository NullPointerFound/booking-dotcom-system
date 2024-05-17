package com.booking.system.customer.domain.core.exception;

import com.booking.system.commons.domain.message.ApplicationMessage;

import java.io.Serial;

public class CustomerNotFoundException extends CustomerDomainException {
    @Serial
    private static final long serialVersionUID = -1641830298069605965L;

    public CustomerNotFoundException() {
        super(ApplicationMessage.CUSTOMER_NOT_FOUND);
    }

    public CustomerNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
