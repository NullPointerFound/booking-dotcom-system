package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@SuperBuilder
public final class CustomerBookingCompletedEvent extends CustomerBookingStatusUpdatedEvent {

    public CustomerBookingCompletedEvent(
            final String reservationOrderId,
            final String customerId,
            final CustomerReservationStatus status
    ) {
        super(reservationOrderId, customerId, status);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
