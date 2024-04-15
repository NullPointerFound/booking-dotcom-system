package com.booking.system.hotel.service.domain.ports.mapper;


import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.domain.entity.Hotel;

public interface HotelUseCaseMapper {

    Hotel registerHotelInputToHotel(RegisterHotelInput input);

    RegisterHotelOutput hotelToRegisterHotelOutput(Hotel hotel);

}
