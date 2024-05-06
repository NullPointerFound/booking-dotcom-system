package com.booking.system.hotel.service.dataacess.db.queries;

import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import com.booking.system.hotel.service.dataacess.db.entity.RoomEntity;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableRoomQueryResult;

public class SearchHotelAvailableRoomAdapter implements SearchHotelAvailableRoomQueryResult {

    private final RoomEntity roomEntity;

    public SearchHotelAvailableRoomAdapter(final RoomEntity roomEntity) {this.roomEntity = roomEntity;}

    @Override
    public RoomId getRoomId() {
        return RoomId.of(this.roomEntity.getId());
    }

    @Override
    public String getRoomName() {
        return this.roomEntity.getName();
    }

    @Override
    public String getRoomDescription() {
        return this.roomEntity.getDescription();
    }

    @Override
    public Integer getRoomCapacity() {
        return this.roomEntity.getCapacity();
    }

    @Override
    public Money getRoomCurrentPrice() {
        return Money.of(this.roomEntity.getCurrentPrice());
    }

    @Override
    public Integer getRoomQuantity() {
        return this.roomEntity.getQuantity();
    }
}

