package com.booking.system.hotel.service.dataacess.db.repository.adapters;

import com.booking.system.hotel.service.domain.domain.entity.Hotel;
import com.booking.system.hotel.service.domain.domain.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {


    @Override
    public void register(Hotel newHotel) {

    }

    @Override
    public boolean existsCategoryById(HotelCategoryId hotelCategoryId) {
        return false;
    }
}
