package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.commons.domain.core.event.CustomerBookingInitiatedEvent;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import com.booking.system.commons.domain.message.ApplicationMessage;
import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomInput;
import com.booking.system.hotel.service.domain.application_service.dto.BookingRoomOutput;
import com.booking.system.hotel.service.domain.core.entity.Room;
import com.booking.system.hotel.service.domain.core.entity.Rooms;
import com.booking.system.commons.domain.core.event.BookingRoomItemRepresentation;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;
import com.booking.system.hotel.service.domain.core.exception.HotelDomainException;
import com.booking.system.hotel.service.domain.ports.api.usecase.BookingRoomRequestUseCase;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.BookingRoomRequestedPublisher;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import com.booking.system.hotel.service.domain.ports.spi.repository.HotelRepository;
import com.booking.system.hotel.service.domain.application_service.dto.BookRoomItemInput;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookingRoomRequestUseCaseImpl implements BookingRoomRequestUseCase {

    private static final BinaryOperator<Integer> BINARY_FUNCTION_IDENTITY = (currentValue, newValue) -> currentValue;
    private final HotelRepository hotelRepository;
    private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatePublisher;
    private final BookingRoomRequestedPublisher bookingRoomRequestedPublisher;


    public BookingRoomRequestUseCaseImpl(
            final HotelRepository hotelRepository,
            final BookingRoomRequestedPublisher bookingRoomRequestedPublisher,
            final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatePublisher
    ) {
        this.bookingRoomRequestedPublisher = bookingRoomRequestedPublisher;
        this.hotelRepository = hotelRepository;
        this.customerBookingRoomUpdatePublisher = customerBookingRoomUpdatePublisher;
    }


    private List<RoomId> mapToRoomIds(final BookingRoomInput input) {
        return input.rooms().stream()
                .map(BookRoomItemInput::roomId)
                .map(RoomId::of)
                .collect(Collectors.toList());
    }

    @Override
    public BookingRoomOutput execute(final BookingRoomInput input) {
        final var rooms = this.hotelRepository.findAllRoomsById(this.mapToRoomIds(input));
        final var reservationOrderId = ReservationOrderId.newInstance();
        this.validateRooms(input.rooms(), rooms);
        this.validateGuest(input, rooms);

        this.bookingRoomRequestedPublisher.publish(
                BookingRoomRequestedEvent.builder()
                        .reservationOrderId(reservationOrderId.getValue().toString())
                        .customerId(input.customerId())
                        .guests(input.guests())
                        .price(this.getTotalPrice(input.rooms(), rooms).getValue())
                        .checkIn(input.checkIn())
                        .checkOut(input.checkOut())
                        .rooms(
                                input.rooms().stream()
                                        .map(r ->
                                                BookingRoomItemRepresentation.builder()
                                                        .roomId(r.roomId())
                                                        .quantity(r.roomQuantity())
                                                        .price(this.getItemPrice(r, rooms).getValue())
                                                        .build()
                                        )
                                        .collect(Collectors.toList())
                        )
                        .build()
        );

        this.customerBookingRoomUpdatePublisher.publish(
                CustomerBookingInitiatedEvent.builder()
                        .customerId(input.customerId())
                        .reservationOrderId(reservationOrderId.getValue().toString())
                        .hotelId(input.hotelId())
                        .guests(input.guests())
                        .totalPrice(this.getTotalPrice(input.rooms(), rooms).getValue())
                        .checkIn(input.checkIn())
                        .checkOut(input.checkOut())
                        .status(CustomerReservationStatus.AWAITING_RESERVATION)
                        .rooms(
                                input.rooms().stream()
                                        .map(r -> BookingRoomItemRepresentation.builder()
                                                .roomId(r.roomId())
                                                .quantity(r.roomQuantity())
                                                .price(this.getItemPrice(r, rooms).getValue())
                                                .build()
                                        )
                                        .collect(Collectors.toList())
                        )
                        .build()
        );

        return new BookingRoomOutput(reservationOrderId.getValue());
    }

    private Money getItemPrice(final BookRoomItemInput room, final Collection<? extends Room> rooms) {
        return rooms.stream()
                .filter(r -> r.getId().getValue().toString().equals(room.roomId()))
                .map(Room::getCurrentPrice)
                .findFirst()
                .orElseThrow(() -> new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));
    }

    private Money getTotalPrice(final Collection<BookRoomItemInput> input, final Collection<? extends Room> rooms) {
        final var quantityByRoomId = this.groupQuantityByRoomId(input);
        return rooms.stream()
                .map(room -> {
                    final var quantity = quantityByRoomId.get(room.getId());
                    return room.getCurrentPrice().multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(Money.ZERO, Money::add);
    }

    private Map<RoomId, Integer> groupQuantityByRoomId(final Collection<BookRoomItemInput> input) {
        return input.stream()
                .collect(Collectors.toMap(
                        room -> RoomId.of(room.roomId()),
                        BookRoomItemInput::roomQuantity,
                        BINARY_FUNCTION_IDENTITY
                ));
    }

    private void validateGuest(final BookingRoomInput input, final Collection<? extends Room> rooms) {
        final var guests = input.guests();

        final Function<String, Integer> getRoomDto = (roomId) -> input.rooms().stream()
                .filter(r -> roomId.equals(r.roomId()))
                .findFirst()
                .map(BookRoomItemInput::roomQuantity)
                .orElseThrow(() -> new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));

        final var totalRoomsCapacity = rooms.stream()
                .mapToInt(room -> room.getCapacity() * getRoomDto.apply(room.getId().toString()))
                .sum();

        if (guests > totalRoomsCapacity) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_BOOKING_GUESTS_EXCEEDED);
        }
    }


    private void validateRooms(final Collection<BookRoomItemInput> input, final Rooms rooms) {
        final var roomsQuantityGroupedById = this.groupQuantityByRoomId(input);

        final var roomIds = input.stream()
                .map(BookRoomItemInput::roomId)
                .map(RoomId::of)
                .toList();

        final var hasUnknownRoom = roomIds.stream()
                .anyMatch(rooms::notContainsId);

        if (hasUnknownRoom) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND);
        }

        for (final Room room : rooms) {
            final var maybeRoomQuantity = Optional.ofNullable(roomsQuantityGroupedById.getOrDefault(
                    room.getId(),
                    null
            ));
            if (maybeRoomQuantity.isEmpty()) {
                throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND);
            }
            if (!room.hasQuantityAvailable(maybeRoomQuantity.get())) {
                throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_EXCEEDED);
            }

        }
    }

}
