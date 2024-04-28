package com.booking.system.booking.service.domain.application_service.service;

import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.ports.repository.BookingRepository;
import com.booking.system.booking.service.domain.ports.repository.RoomRepository;
import com.booking.system.commons.domain.core.valueobject.BookingStatus;
import com.booking.system.commons.domain.core.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;
import com.booking.system.booking.service.domain.core.entity.BookingRoom;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
public class VerifyRoomAvailability {


    private static final String ROOM_UNAVAILABLE_TEMPLATE_MESSAGE = "The room roomId={0} is no longer available for period {1}";
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public VerifyRoomAvailability(final BookingRepository bookingRepository, final RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public FailureMessages execute(final Booking booking) {
        final var failureMessages = FailureMessages.empty();

        final var rooms = this.roomRepository.findAllByRoomId(booking.getRoomsId());

        log.info("Verifying rooms availability reservationOrderId={}", booking.getReservationOrderId());
        for (final var bookingRoom : booking.getBookingRooms()) {
            final var bookings = this.bookingRepository.findBookingByRoomIdAndPeriod(
                    bookingRoom.getRoomId(),
                    booking.getBookingPeriod()
            );
            final var bookingsOnPeriod = bookings.stream()
                    .filter(item -> item.getStatus() != BookingStatus.CANCELED)
                    .filter(item -> booking.isBookingPeriodContainedIn(item.getBookingPeriod()))
                    .toList();

            final var allBookingRoomsGroupedByRoomId = bookingsOnPeriod.stream()
                    .flatMap(item -> item.getBookingRooms().stream())
                    .collect(Collectors.groupingBy(BookingRoom::getRoomId));

            for (final var room : rooms) {
                final var bookingsForRoom = allBookingRoomsGroupedByRoomId.getOrDefault(room.getId(), new ArrayList<>());
                if (room.getQuantity() == bookingsForRoom.size()) {
                    log.error(
                            "The roomId={} is unavailable for period={}, reservationOrderId={}",
                            bookingRoom.getRoomId(),
                            booking.getBookingPeriod().periodAsString(),
                            booking.getReservationOrderId()
                    );
                    failureMessages.add(MessageFormat.format(
                            ROOM_UNAVAILABLE_TEMPLATE_MESSAGE,
                            room.getId(),
                            booking.getBookingPeriod().periodAsString()
                    ));
                }
            }
        }

        return failureMessages;
    }

}