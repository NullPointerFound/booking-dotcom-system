package com.booking.system.commons.domain.core.valueobject;

import java.math.BigDecimal;

public class Money {

    public static final Money ZERO = Money.of(BigDecimal.ZERO);
    private final BigDecimal value;

    private Money(final BigDecimal value) {
        this.value = value;
    }

    public static Money of(final BigDecimal value) {
        return new Money(value);
    }

    public static Money of(final Integer value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money of(final Double value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public boolean isNegative() {
        return this.value.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isZero() {
        return this.value.compareTo(BigDecimal.ZERO) == 0;
    }

    public Money multiply(final BigDecimal value) {
        return new Money(this.value.multiply(value));
    }

    public Money add(final Money money) {
        return new Money(this.value.add(money.value));
    }

    public boolean isNotEqual(final Money money) {
        return this.value.compareTo(money.value) != 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        final Money money = (Money) o;

        return this.value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
