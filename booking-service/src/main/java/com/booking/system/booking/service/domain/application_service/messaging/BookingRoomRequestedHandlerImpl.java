package com.booking.system.booking.service.domain.application_service.messaging;

import com.booking.system.booking.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomRequestedHandler;
import com.booking.system.booking.service.domain.ports.api.usecase.BookingRoomUseCase;
import com.booking.system.booking.service.domain.ports.spi.messaging.publiser.BookingRoomResponsePublisher;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;

public class BookingRoomRequestedHandlerImpl implements BookingRoomRequestedHandler {

    private final BookingRoomUseCase bookingRoomUseCase;
    private final BookingUseCaseMapper mapper;
    private final BookingRoomResponsePublisher publisher;

    public BookingRoomRequestedHandlerImpl(
            final BookingRoomUseCase bookingRoomUseCase,
            final BookingUseCaseMapper mapper,
            final BookingRoomResponsePublisher publisher
    ) {
        this.bookingRoomUseCase = bookingRoomUseCase;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public void handle(final BookingRoomRequestedEvent event) {
        final var input = this.mapper.bookingRoomRequestedEventToBookingRoomInput(event);
        final var output = this.bookingRoomUseCase.execute(input);

        final var bookingRoomResponseEvent = this.mapOutputToEvent(output);

        this.publisher.publish(bookingRoomResponseEvent);
    }

    private BookingRoomResponseEvent mapOutputToEvent(final BookingRoomOutput output) {
        if (output.failureMessages().isNotEmpty()) {
            return this.mapper.bookingRoomOutputToBookingRoomFailedEvent(output);
        } else {
            return this.mapper.bookingRoomOutputToBookingRoomResponseEvent(output);
        }
    }

}
