package com.booking.system.hotel.service.domain.ports.repository;

import com.booking.system.hotel.service.domain.domain.entity.Hotel;
import com.booking.system.hotel.service.domain.domain.valueobject.HotelCategoryId;


public interface HotelRepository {

    void register( Hotel newHotel);

    boolean existsCategoryById(HotelCategoryId hotelCategoryId);


}
