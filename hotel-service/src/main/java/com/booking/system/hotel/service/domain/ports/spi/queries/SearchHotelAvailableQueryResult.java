package com.booking.system.hotel.service.domain.ports.spi.queries;

import com.booking.system.commons.domain.core.valueobject.HotelId;

import java.util.List;

public interface SearchHotelAvailableQueryResult {
    HotelId getHotelId();

    String getHotelName();

    String getHotelDescription();

    String getHotelZip();

    String getHotelStreet();

    String getHotelCategoryName();

    String getHotelCity();

    String getHotelState();

    String getHotelCountry();

    List<SearchHotelAvailableRoomQueryResult> getRooms();
}
