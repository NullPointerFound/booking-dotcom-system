package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainEntityId;

import java.util.UUID;

public class PaymentId extends AbstractDomainEntityId<UUID> {
    protected PaymentId(final UUID value) {
        super(value);
    }

    public static PaymentId newInstance() {
        return new PaymentId(UUID.randomUUID());
    }

    public static PaymentId of(final UUID value) {
        return new PaymentId(value);
    }

    public static PaymentId of(final String rawValue) {
        return new PaymentId(UUID.fromString(rawValue));
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
