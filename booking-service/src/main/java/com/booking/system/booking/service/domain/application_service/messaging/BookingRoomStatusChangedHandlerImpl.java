package com.booking.system.booking.service.domain.application_service.messaging;


import com.booking.system.booking.service.domain.application_service.dto.UpdateBookingStatusOutput;
import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.booking.system.booking.service.domain.ports.api.usecase.UpdateBookingStatusUseCase;
import com.booking.system.booking.service.domain.ports.spi.messaging.publiser.BookingRoomResponsePublisher;
import com.booking.system.commons.domain.core.event.BookingRoomConfirmedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomPaymentCompleted;
import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import lombok.extern.slf4j.Slf4j;
import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;

@Slf4j
public class BookingRoomStatusChangedHandlerImpl implements BookingRoomStatusChangedHandler {

    private final BookingUseCaseMapper bookingUseCaseMapper;
    private final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase;
    private final BookingRoomResponsePublisher bookingRoomResponsePublisher;

    public BookingRoomStatusChangedHandlerImpl(
            final BookingUseCaseMapper bookingUseCaseMapper,
            final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase,
            final BookingRoomResponsePublisher bookingRoomResponsePublisher
    ) {
        this.bookingUseCaseMapper = bookingUseCaseMapper;
        this.updateBookingRoomStatusUseCase = updateBookingRoomStatusUseCase;
        this.bookingRoomResponsePublisher = bookingRoomResponsePublisher;
    }

    @Override
    public void handle(final BookingRoomStatusUpdatedEvent event) {
        log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);

        final var input = this.bookingUseCaseMapper.bookingRoomStatusUpdatedEventToUpdateBookingRoomStatusInput(event);
        final var output = this.updateBookingRoomStatusUseCase.execute(input);
        log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);
        if (event instanceof BookingRoomPaymentCompleted) {
            log.info(
                    "Payment completed, sending Booking confirmation to Hotel Service | reservationOrderId={}",
                    event.getReservationOrderId()
            );
            this.bookingRoomResponsePublisher.publish(this.bookingRoomStatusOutputToBookingRoomConfirmedEvent(output));
            log.info(
                    "Hotel service notified | reservationOrderId={}",
                    event.getReservationOrderId()
            );
        }
    }

    private BookingRoomResponseEvent bookingRoomStatusOutputToBookingRoomConfirmedEvent(final UpdateBookingStatusOutput output) {
        return new BookingRoomConfirmedEvent(
                output.reservationOrderId(),
                output.customerId(),
                CustomerReservationStatus.RESERVED
        );
    }
}
