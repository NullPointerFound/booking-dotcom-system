package com.booking.system.hotel.service.domain.application_service.mapper;

import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.hotel.service.domain.application_service.dto.*;
import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.core.entity.Room;
import com.booking.system.hotel.service.domain.core.entity.Rooms;
import com.booking.system.hotel.service.domain.core.valueobject.HotelAddress;
import com.booking.system.hotel.service.domain.core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.core.valueobject.LocalityId;
import com.booking.system.hotel.service.domain.ports.api.mapper.HotelUseCaseMapper;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableQueryResult;

import java.util.stream.Collectors;

public class HotelUseCaseMapperImpl implements HotelUseCaseMapper {

    @Override
    public Hotel registerHotelInputToHotel(final RegisterHotelInput input) {
        final var address = HotelAddress.builder()
                .street(input.street())
                .zip(input.zip())
                .build();
        return Hotel.builder()
                .id(null)
                .name(input.name())
                .description(input.description())
                .address(address)
                .categoryId(HotelCategoryId.of(input.categoryId()))
                .localityId(LocalityId.of(input.localityId()))
                .rooms(
                        Rooms.newInstance(
                                input.rooms().stream()
                                        .map(HotelUseCaseMapperImpl::registerHotelRoomInputToRoom)
                                        .collect(Collectors.toList())
                        ))
                .build();
    }

    @Override
    public RegisterHotelOutput hotelToRegisterHotelOutput(final Hotel hotel) {
        return new RegisterHotelOutput(hotel.getId().toString(), hotel.getRooms().asRawIds());
    }

    @Override
    public SearchHotelAvailableOutput searchHotelAvailableQueryResultToSearchHotelAvailableOutput(SearchHotelAvailableQueryResult queryResult) {

        return SearchHotelAvailableOutput.builder()
                .id(queryResult.getHotelId().toString())
                .name(queryResult.getHotelName())
                .description(queryResult.getHotelDescription())
                .address(queryResult.getHotelStreet() + " - " + queryResult.getHotelZip())
                .category(queryResult.getHotelCategoryName())
                .city(queryResult.getHotelCity())
                .state(queryResult.getHotelState())
                .country(queryResult.getHotelCountry())
                .rooms(
                        queryResult.getRooms().stream()
                                .map(roomQueryResult -> SearchHotelAvailableRoomOutput.builder()
                                        .id(roomQueryResult.getRoomId().toString())
                                        .name(roomQueryResult.getRoomName())
                                        .description(roomQueryResult.getRoomDescription())
                                        .currentPrice(roomQueryResult.getRoomCurrentPrice().getValue())
                                        .quantity(roomQueryResult.getRoomQuantity())
                                        .capacity(roomQueryResult.getRoomCapacity())
                                        .build()
                                )
                        .toList())
                .build();
    }

    private static Room registerHotelRoomInputToRoom(final RegisterHotelRoomInput input) {
        return Room.builder()
                .id(null)
                .hotelId(null)
                .name(input.name())
                .description(input.description())
                .currentPrice(Money.of(input.currentPrice()))
                .capacity(input.capacity())
                .quantity(input.quantity())
                .build();
    }
}
