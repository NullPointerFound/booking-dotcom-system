package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public final class PaymentCompletedEvent extends PaymentResponseEvent {

    @Builder.Default
    private final Instant createdAt = Instant.now();
    private final String reservationOrderId;
    private final String customerId;
    private final BigDecimal totalPrice;
    private final PaymentStatus status;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}