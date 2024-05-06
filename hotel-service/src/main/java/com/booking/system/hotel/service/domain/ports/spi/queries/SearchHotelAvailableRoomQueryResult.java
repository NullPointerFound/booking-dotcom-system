package com.booking.system.hotel.service.domain.ports.spi.queries;

import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.RoomId;

public interface SearchHotelAvailableRoomQueryResult {

    RoomId getRoomId();

    String getRoomName();

    String getRoomDescription();

    Integer getRoomCapacity();

    Money getRoomCurrentPrice();

    Integer getRoomQuantity();

}
