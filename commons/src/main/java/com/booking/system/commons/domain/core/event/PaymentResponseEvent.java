package com.booking.system.commons.domain.core.event;

public abstract sealed class PaymentResponseEvent permits PaymentCompletedEvent, PaymentFailedEvent {
}
