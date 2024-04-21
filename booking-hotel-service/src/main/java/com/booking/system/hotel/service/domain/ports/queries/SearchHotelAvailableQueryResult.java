package com.booking.system.hotel.service.domain.ports.queries;

import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.hotel.service.domain.domain.entity.Room;

import java.util.List;

public interface SearchHotelAvailableQueryResult {
    HotelId getHotelId();

    String getHotelName();

    String getHotelDescription();

    String getHotelCep();

    String getHotelStreet();

    String getHotelCategoryName();

    String getHotelCity();

    String getHotelState();

    String getHotelCountry();

    List<SearchHotelAvailableRoomQueryResult> getRooms();
}
