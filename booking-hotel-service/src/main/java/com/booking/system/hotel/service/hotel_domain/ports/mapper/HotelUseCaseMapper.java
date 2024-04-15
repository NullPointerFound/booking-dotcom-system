package com.booking.system.hotel.service.hotel_domain.ports.mapper;


import com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.hotel_domain.hotel_domain_core.entity.Hotel;

public interface HotelUseCaseMapper {

    Hotel registerHotelInputToHotel(RegisterHotelInput input);

    RegisterHotelOutput hotelToRegisterHotelOutput(Hotel hotel);

}
