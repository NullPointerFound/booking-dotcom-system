package com.booking.system.commons.domain.core.event;

import com.booking.system.commons.domain.core.valueobject.BookingStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Getter
@SuperBuilder
public final class BookingRoomPaymentFailed extends BookingRoomStatusUpdatedEvent {

    private final List<String> failureMessages;

    public BookingRoomPaymentFailed(
            final String reservationOrderId,
            final String customerId,
            final BookingStatus status,
            final List<String> failureMessages
    ) {
        super(reservationOrderId, customerId, status);
        this.failureMessages = failureMessages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
