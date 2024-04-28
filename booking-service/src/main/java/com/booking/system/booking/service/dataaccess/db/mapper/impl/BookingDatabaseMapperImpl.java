package com.booking.system.booking.service.dataaccess.db.mapper.impl;


import com.booking.system.booking.service.dataaccess.db.entity.BookingEntity;
import com.booking.system.booking.service.dataaccess.db.entity.BookingRoomEntity;
import com.booking.system.booking.service.dataaccess.db.entity.RoomEntity;
import com.booking.system.booking.service.dataaccess.db.mapper.BookingDatabaseMapper;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.BookingPeriod;
import com.booking.system.booking.service.domain.core.entity.BookingRoom;
import com.booking.system.booking.service.domain.core.entity.Room;
import com.booking.system.booking.service.domain.core.valueobject.BookingId;
import com.booking.system.booking.service.domain.core.valueobject.BookingRoomId;
import com.booking.system.commons.domain.core.valueobject.*;



import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookingDatabaseMapperImpl implements BookingDatabaseMapper {


    @Override
    public Booking bookingEntityToBooking(final BookingEntity entity) {
        return Booking.builder()
                .id(BookingId.of(entity.getId()))
                .customerId(CustomerId.of(entity.getCustomerId()))
                .totalPrice(Money.of(entity.getTotalPrice()))
                .reservationOrderId(ReservationOrderId.of(entity.getReservationOrderId()))
                .bookingPeriod(BookingPeriod.of(entity.getCheckIn(), entity.getCheckOut()))
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .bookingRooms(
                        entity.getBookingRooms().stream()
                                .map(this::bookingRoomEntityToBookingRoom)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private BookingRoom bookingRoomEntityToBookingRoom(final BookingRoomEntity entity) {
        return BookingRoom.builder()
                .id(BookingRoomId.of(entity.getId()))
                .bookingId(BookingId.of(entity.getBooking().getId()))
                .roomId(RoomId.of(entity.getRoom().getId()))
                .price(Money.of(entity.getPrice()))
                .quantity(entity.getQuantity())
                .build();
    }

    private BookingRoomEntity bookingRoomToBookingRoomEntity(final BookingRoom entity) {
        return BookingRoomEntity.builder()
                .id(entity.getId().getValue())
                .price(entity.getPrice().getValue())
                .quantity(entity.getQuantity())
                //Todo: adding .room(this.roomRepository.findRoomEntityById(entity.getRoomId()))
                .build();
    }


    @Override
    public BookingEntity bookingToBookingEntity(final Booking booking) {
        final var bookingEntity = BookingEntity.builder()
                .id(booking.getId().getValue())
                .customerId(booking.getCustomerId().getValue())
                .reservationOrderId(booking.getReservationOrderId().getValue())
                .status(booking.getStatus())
                .totalPrice(booking.getTotalPrice().getValue())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .checkIn(booking.getBookingPeriod().getCheckIn())
                .checkOut(booking.getBookingPeriod().getCheckOut())
                .build();
        final var bookingRoomEntities = booking.getBookingRooms().stream()
                .map(this::bookingRoomToBookingRoomEntity)
                .collect(Collectors.toSet());
        bookingRoomEntities.forEach(bookingRoom -> bookingRoom.setBooking(bookingEntity));
        bookingEntity.setBookingRooms(bookingRoomEntities);

        return bookingEntity;
    }

    @Override
    public Room roomEntityToRoom(final RoomEntity roomEntity) {
        return Room.builder()
                .id(RoomId.of(roomEntity.getId()))
                .hotelId(HotelId.of(roomEntity.getHotelId()))
                .capacity(roomEntity.getCapacity())
                .quantity(roomEntity.getQuantity())
                .build();
    }

}