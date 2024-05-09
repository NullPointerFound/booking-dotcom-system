package com.booking.system.commons.domain.core.event;

public abstract sealed class BookingRoomResponseEvent implements Event
        permits BookingRoomPendingEvent, BookingRoomFailedEvent, BookingRoomConfirmedEvent {
}
