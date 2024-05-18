package com.booking.system.customer.domain.core.valueobject;

import com.booking.system.customer.domain.core.exception.CustomerDomainException;
import com.booking.system.commons.domain.message.ApplicationMessage;

public record Email(
        String value
) {

    public String value() {
        if (value == null || value.isBlank()) {
            throw new CustomerDomainException(ApplicationMessage.CUSTOMER_EMAIL_NOT_NULL);
        }
        return value.toLowerCase();
    }
}
