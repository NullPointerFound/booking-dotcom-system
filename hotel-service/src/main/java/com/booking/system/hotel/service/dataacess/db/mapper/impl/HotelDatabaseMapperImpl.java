package com.booking.system.hotel.service.dataacess.db.mapper.impl;

import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.hotel.service.dataacess.db.entity.HotelCategoryEntity;
import com.booking.system.hotel.service.dataacess.db.entity.HotelEntity;
import com.booking.system.hotel.service.dataacess.db.entity.LocalityEntity;
import com.booking.system.hotel.service.dataacess.db.entity.RoomEntity;
import com.booking.system.hotel.service.dataacess.db.mapper.HotelDatabaseMapper;
import com.booking.system.hotel.service.dataacess.db.queries.SearchHotelAvailableAdapter;
import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.core.entity.Room;
import com.booking.system.hotel.service.domain.core.entity.Rooms;
import com.booking.system.hotel.service.domain.core.valueobject.HotelAddress;
import com.booking.system.hotel.service.domain.core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.core.valueobject.LocalityId;
import com.booking.system.hotel.service.domain.core.valueobject.RoomId;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableQueryResult;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HotelDatabaseMapperImpl implements HotelDatabaseMapper {

    @Override
    public Hotel hotelEntityToHotel(final HotelEntity hotelEntity) {
        return Hotel.builder()
                .id(HotelId.of(hotelEntity.getId()))
                .name(hotelEntity.getName())
                .description(hotelEntity.getDescription())
                .categoryId(HotelCategoryId.of(hotelEntity.getCategory().getId()))
                .localityId(LocalityId.of(hotelEntity.getId()))
                .address(new HotelAddress(hotelEntity.getHotelZip(), hotelEntity.getHotelStreet()))
                .rooms(Rooms.newInstance(
                        hotelEntity.getRooms().stream()
                                .map(this::roomEntityToRoom)
                                .toList()
                ))
                .build();
    }

    @Override
    public HotelEntity hotelToHotelEntity(final Hotel hotel) {
        return HotelEntity.builder()
                .id(hotel.getId().getValue())
                .hotelZip(hotel.getAddress().getZip())
                .hotelStreet(hotel.getAddress().getStreet())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .category(
                        HotelCategoryEntity.builder()
                                .id(hotel.getCategoryId().getValue())
                                .build()
                )
                .locality(
                        LocalityEntity.builder()
                                .id(hotel.getLocalityId().getValue())
                                .build()
                )
                .build();
    }

    @Override
    public Room roomEntityToRoom(final RoomEntity room) {
        return Room.builder()
                .id(RoomId.of(room.getId()))
                .hotelId(HotelId.of(room.getHotel().getId()))
                .name(room.getName())
                .description(room.getDescription())
                .currentPrice(Money.of(room.getCurrentPrice()))
                .capacity(room.getCapacity())
                .quantity(room.getQuantity())
                .build();
    }

    @Override
    public RoomEntity roomToRoomEntity(final Room room) {
        return RoomEntity.builder()
                .id(room.getId().getValue())
                .currentPrice(room.getCurrentPrice().getValue())
                .name(room.getName())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .quantity(room.getQuantity())
                .hotel(
                        HotelEntity.builder()
                                .id(room.getHotelId().getValue())
                                .build()
                )
                .build();
    }

    @Override
    public Set<RoomEntity> roomsToRoomEntitySet(final Collection<? extends Room> rooms) {
        return rooms.stream()
                .map(this::roomToRoomEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public SearchHotelAvailableQueryResult hotelEntityToSearchHotelAvailableQueryResult(final HotelEntity hotelEntity) {
        return new SearchHotelAvailableAdapter(hotelEntity);
    }

}
