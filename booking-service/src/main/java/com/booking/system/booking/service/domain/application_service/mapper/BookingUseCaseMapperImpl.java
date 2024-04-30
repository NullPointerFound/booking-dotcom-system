package com.booking.system.booking.service.domain.application_service.mapper;

import com.booking.system.booking.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.booking.service.domain.application_service.dto.BookingRoomItemInput;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.BookingPeriod;
import com.booking.system.booking.service.domain.core.entity.BookingRoom;
import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
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

    private BookingRoom bookingRoomItemInputToBookingRoom(final BookingRoomItemInput bookingRoomItemInput) {
        return BookingRoom.builder()
                        .roomId(RoomId.of(bookingRoomItemInput.roomId()))
                                        .quantity(bookingRoomItemInput.quantity())
                                                .price(Money.of(bookingRoomItemInput.price())).build();
    }
}
