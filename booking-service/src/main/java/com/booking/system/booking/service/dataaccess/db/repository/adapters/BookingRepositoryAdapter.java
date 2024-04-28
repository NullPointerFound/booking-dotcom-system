package com.booking.system.booking.service.dataaccess.db.repository.adapters;

import com.booking.system.booking.service.dataaccess.db.mapper.BookingDatabaseMapper;
import com.booking.system.booking.service.dataaccess.db.repository.BookingJpaRepository;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.BookingPeriod;
import com.booking.system.booking.service.domain.ports.repository.BookingRepository;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookingRepositoryAdapter implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;
    private final BookingDatabaseMapper bookingDatabaseMapper;

    public BookingRepositoryAdapter(BookingJpaRepository bookingJpaRepository, BookingDatabaseMapper bookingDatabaseMapper) {
        this.bookingJpaRepository = bookingJpaRepository;
        this.bookingDatabaseMapper = bookingDatabaseMapper;
    }


    @Override
    public List<Booking> findBookingByRoomIdAndPeriod(final RoomId roomId, final BookingPeriod bookingPeriod) {
        final var bookings = this.bookingJpaRepository.findByRoomIdAndPeriod(
                roomId.getValue(),
                bookingPeriod.getCheckIn(),
                bookingPeriod.getCheckOut()
        );
        return bookings.stream()
                .map(this.bookingDatabaseMapper::bookingEntityToBooking)
                .collect(Collectors.toList());
    }

    @Override
    public void save(final Booking booking) {
        final var bookingEntity = this.bookingDatabaseMapper.bookingToBookingEntity(booking);
        this.bookingJpaRepository.save(bookingEntity);
    }

    @Override
    public Optional<Booking> findBookingByReservationOrderId(final ReservationOrderId reservationOrderId) {
        return this.bookingJpaRepository.findByReservationOrderId(reservationOrderId.getValue())
                .map(this.bookingDatabaseMapper::bookingEntityToBooking);
    }
}
