package com.booking.system.booking.service.domain.application_service.mapper;

import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomItemInput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.BookingPeriod;
import com.booking.system.booking.service.domain.core.entity.BookingRoom;
import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
import com.booking.system.commons.domain.core.event.BookingRoomFailedEvent;
import com.booking.system.commons.domain.core.event.BookingRoomItemRepresentation;
import com.booking.system.commons.domain.core.event.BookingRoomPendingEvent;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;
import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.commons.domain.core.valueobject.RoomId;

import java.util.stream.Collectors;

public class BookingUseCaseMapperImpl implements BookingUseCaseMapper {


    @Override
    public Booking bookingRoomInputToBooking(final BookingRoomInput input) {
        return Booking.builder()
                .reservationOrderId(ReservationOrderId.of(input.reservationOrderId()))
                .customerId(CustomerId.of(input.customerId()))
                .bookingPeriod(BookingPeriod.of(input.checkIn(), input.checkOut()))
                .totalPrice(Money.of(input.price()))
                .bookingRooms(
                        input.rooms().stream()
                                .map(this::bookingRoomItemInputToBookingRoom)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public BookingRoomInput bookingRoomRequestedEventToBookingRoomInput(
            final BookingRoomRequestedEvent event
    ) {
        return BookingRoomInput.builder()
                .reservationOrderId(event.getReservationOrderId())
                .customerId(event.getCustomerId())
                .price(event.getPrice())
                .guests(event.getGuests())
                .checkIn(event.getCheckIn())
                .checkOut(event.getCheckOut())
                .rooms(
                        event.getRooms().stream()
                                .map(item -> new BookingRoomItemInput(
                                                item.getRoomId(),
                                                item.getQuantity(),
                                                item.getPrice()
                                        )
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }

    private BookingRoom bookingRoomItemInputToBookingRoom(
            final BookingRoomItemInput item
    ) {
        return BookingRoom.builder()
                .roomId(RoomId.of(item.roomId()))
                .quantity(item.quantity())
                .price(Money.of(item.price()))
                .build();
    }
    private BookingRoomItemRepresentation bookingRoomItemInputToBookingRoom(final BookingRoom bookingRoom) {
        return BookingRoomItemRepresentation.builder()
                .roomId(bookingRoom.getRoomId().toString())
                .quantity(bookingRoom.getQuantity())
                .price(bookingRoom.getPrice().getValue())
                .build();
    }
}
