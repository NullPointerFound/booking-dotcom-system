package com.booking.system.hotel.service.domain.ports.repository;

import com.booking.system.hotel.service.domain.domain.entity.Hotel;


public interface HotelRepository {

    void register( Hotel newHotel);

}
