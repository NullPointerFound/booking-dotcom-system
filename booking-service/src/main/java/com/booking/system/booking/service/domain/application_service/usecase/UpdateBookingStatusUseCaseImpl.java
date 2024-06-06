package com.booking.system.booking.service.domain.application_service.usecase;

import com.booking.system.booking.service.domain.application_service.dto.UpdateBookingStatusInput;
import com.booking.system.booking.service.domain.application_service.dto.UpdateBookingStatusOutput;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.exception.BookingDomainException;
import com.booking.system.booking.service.domain.ports.api.usecase.UpdateBookingStatusUseCase;
import com.booking.system.booking.service.domain.ports.spi.repository.BookingRepository;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.commons.domain.message.ApplicationMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateBookingStatusUseCaseImpl implements UpdateBookingStatusUseCase {

    private final BookingRepository bookingRepository;

    public UpdateBookingStatusUseCaseImpl(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UpdateBookingStatusOutput execute(final UpdateBookingStatusInput input) {
        final var booking = this.findBookingBy(input.reservationOrderId());
        booking.changeStatusTo(input.status());
        this.bookingRepository.save(booking);
        return new UpdateBookingStatusOutput(
                booking.getReservationOrderId().toString(),
                booking.getCustomerId().toString(),
                booking.getStatus()
        );
    }

    private Booking findBookingBy(final ReservationOrderId reservationOrderId) {
        return this.bookingRepository.findBookingByReservationOrderId(reservationOrderId)
                .orElseThrow(() -> new BookingDomainException(ApplicationMessage.BOOKING_NOT_FOUND));
    }
}
