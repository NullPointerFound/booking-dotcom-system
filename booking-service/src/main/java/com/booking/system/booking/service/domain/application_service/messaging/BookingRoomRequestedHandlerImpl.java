package com.booking.system.booking.service.domain.application_service.messaging;

import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomRequestedHandler;
import com.booking.system.booking.service.domain.ports.api.usecase.BookingRoomUseCase;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;

public class BookingRoomRequestedHandlerImpl implements BookingRoomRequestedHandler {

    private final BookingRoomUseCase bookingRoomUseCase;
    private final BookingUseCaseMapper mapper;

    public BookingRoomRequestedHandlerImpl(
            final BookingRoomUseCase bookingRoomUseCase,
            final BookingUseCaseMapper mapper
    ) {
        this.bookingRoomUseCase = bookingRoomUseCase;
        this.mapper = mapper;
    }

    @Override
    public void handle(final BookingRoomRequestedEvent event) {
        final var input = this.mapper.bookingRoomRequestedEventToBookingRoomInput(event);
        this.bookingRoomUseCase.execute(input);
    }

}
