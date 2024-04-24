package com.booking.system.hotel.service.domain.ports.repository;

import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.ports.queries.SearchHotelAvailableQueryResult;

import java.util.List;


public interface HotelRepository {

    void register( Hotel newHotel);

    boolean existsCategoryById(HotelCategoryId hotelCategoryId);

    List<SearchHotelAvailableQueryResult> searchHotelAvailableBy(String name, String category, String vity, String state);

}
