package com.booking.system.hotel.service.application.service.impl;

import com.booking.system.hotel.service.application.service.HotelApplicationService;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.ports.usecase.RegisterHotelUseCase;

public class HotelApplicationServiceImpl implements HotelApplicationService {

    RegisterHotelUseCase registerHotelUseCase;

    public HotelApplicationServiceImpl(RegisterHotelUseCase registerHotelUseCase) {
        this.registerHotelUseCase = registerHotelUseCase;
    }

    @Override
    public RegisterHotelOutput register(RegisterHotelInput input) {
        return registerHotelUseCase.execute(input);
    }
}
