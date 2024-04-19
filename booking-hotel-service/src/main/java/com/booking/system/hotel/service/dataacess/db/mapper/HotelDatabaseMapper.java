package com.booking.system.hotel.service.dataacess.db.mapper;

import com.booking.system.hotel.service.dataacess.db.entity.HotelEntity;
import com.booking.system.hotel.service.dataacess.db.entity.RoomEntity;
import com.booking.system.hotel.service.domain.domain.entity.Hotel;
import com.booking.system.hotel.service.domain.domain.entity.Room;

import java.util.Collection;
import java.util.Set;

public interface HotelDatabaseMapper {

    Hotel hotelEntityToHotel(HotelEntity hotelEntity);

    HotelEntity hotelToHotelEntity(Hotel hotel);

    Room roomEntityToRoom(RoomEntity room);

    RoomEntity roomToRoomEntity(Room room);

    Set<RoomEntity> roomsToRoomEntitySet(Collection<? extends Room> rooms);
}