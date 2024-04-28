package com.booking.system.booking.service.domain.application_service.usecase;


import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.booking.service.domain.application_service.service.BookingInitializer;
import com.booking.system.booking.service.domain.application_service.service.VerifyRoomAvailability;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.ports.mapper.BookingUseCaseMapper;
import com.booking.system.booking.service.domain.ports.repository.BookingRepository;
import com.booking.system.booking.service.domain.ports.usecase.BookingRoomUseCase;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.commons.domain.core.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomUseCaseImpl implements BookingRoomUseCase {

    private final BookingRepository bookingRepository;
    private final BookingUseCaseMapper mapper;
    private final VerifyRoomAvailability verifyRoomAvailability;
    private final BookingInitializer bookingInitializer;

    public BookingRoomUseCaseImpl(
            final BookingRepository bookingRepository,
            final BookingUseCaseMapper mapper,
            final VerifyRoomAvailability verifyRoomAvailability,
            final BookingInitializer bookingInitializer
    ) {
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
        this.verifyRoomAvailability = verifyRoomAvailability;
        this.bookingInitializer = bookingInitializer;
    }

    private void initializeAndValidate(final Booking booking, final FailureMessages failureMessages) {
        final var collectedFailureMessages = this.bookingInitializer.execute(booking);
        failureMessages.addAll(collectedFailureMessages);
    }

    @Override
    public BookingRoomOutput execute(final BookingRoomInput input) {
        final var booking = this.mapper.bookingRoomInputToBooking(input);
        final var failureMessages = FailureMessages.empty();
        this.initializeAndValidate(booking, failureMessages);
        this.verifyRoomAvailability(booking, failureMessages);
        if (failureMessages.isNotEmpty()) {
            return new BookingRoomOutput(booking, CustomerReservationStatus.RESERVATION_FAILED, failureMessages);
        }
        this.bookingRepository.save(booking);
        return new BookingRoomOutput(booking, CustomerReservationStatus.AWAITING_PAYMENT, failureMessages);
    }

    private void verifyRoomAvailability(final Booking booking, final FailureMessages failureMessages) {
        final var collectedFailureMessages = this.verifyRoomAvailability.execute(booking);
        failureMessages.addAll(collectedFailureMessages);
    }
}