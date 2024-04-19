package com.booking.system.hotel.service.application.service;

import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;

public interface HotelApplicationService {

    RegisterHotelOutput register(RegisterHotelInput input);

}
