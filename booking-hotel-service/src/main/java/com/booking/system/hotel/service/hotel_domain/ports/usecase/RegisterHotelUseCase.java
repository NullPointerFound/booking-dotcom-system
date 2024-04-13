package com.booking.system.hotel.service.hotel_domain.ports.usecase;

import com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.hotel_domain.hotel_application_service.dto.RegisterHotelOutput;

@FunctionalInterface
public interface RegisterHotelUseCase {

    RegisterHotelOutput execute(RegisterHotelInput input);

}
