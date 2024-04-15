package com.booking.system.hotel.service.domain.ports.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;

@FunctionalInterface
public interface RegisterHotelUseCase {

    RegisterHotelOutput execute(RegisterHotelInput input);
}
