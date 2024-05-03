package com.booking.system.hotel.service.domain.core.event;

import com.booking.system.commons.domain.core.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Getter
@Builder
@AllArgsConstructor
public class BookingRoomRequestedEvent implements Event {

    @Builder.Default
    private final Instant createdAt = Instant.now();
    private final String reservationOrderId;
    private final String customerId;
    private final BigDecimal price;
    private final Integer guests;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
