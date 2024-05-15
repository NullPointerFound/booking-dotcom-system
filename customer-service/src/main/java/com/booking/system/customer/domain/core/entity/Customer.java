package com.booking.system.customer.domain.core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.customer.domain.core.valueobject.Email;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Customer extends AbstractDomainEntity<CustomerId> {

    private final String name;
    private final Email email;

    protected Customer(final CustomerId id, final String name, final Email email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }
}