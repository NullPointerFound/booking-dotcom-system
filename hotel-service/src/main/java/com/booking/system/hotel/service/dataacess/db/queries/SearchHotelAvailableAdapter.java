package com.booking.system.hotel.service.dataacess.db.queries;

import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.hotel.service.dataacess.db.entity.HotelEntity;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableQueryResult;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableRoomQueryResult;

import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelAvailableAdapter implements SearchHotelAvailableQueryResult {

    private final HotelEntity hotelEntity;

    public SearchHotelAvailableAdapter(final HotelEntity hotelEntity) {this.hotelEntity = hotelEntity;}

    @Override
    public HotelId getHotelId() {
        return HotelId.of(this.hotelEntity.getId());
    }

    @Override
    public String getHotelName() {
        return this.hotelEntity.getName();
    }

    @Override
    public String getHotelDescription() {
        return this.hotelEntity.getDescription();
    }

    @Override
    public String getHotelZip() {
        return this.hotelEntity.getHotelZip();
    }

    @Override
    public String getHotelStreet() {
        return this.hotelEntity.getHotelStreet();
    }

    @Override
    public String getHotelCategoryName() {
        return this.hotelEntity.getCategory().getName();
    }

    @Override
    public String getHotelCity() {
        return this.hotelEntity.getLocality().getCity();

    }

    @Override
    public String getHotelState() {
        return this.hotelEntity.getLocality().getState();
    }

    @Override
    public String getHotelCountry() {
        return this.hotelEntity.getLocality().getCountry();
    }

    @Override
    public List<SearchHotelAvailableRoomQueryResult> getRooms() {
        return this.hotelEntity.getRooms().stream()
                .map(SearchHotelAvailableRoomAdapter::new)
                .collect(Collectors.toList());
    }
}
