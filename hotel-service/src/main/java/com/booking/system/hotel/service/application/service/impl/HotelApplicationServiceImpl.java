package com.booking.system.hotel.service.application.service.impl;

import com.booking.system.hotel.service.application.service.HotelApplicationService;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;
import com.booking.system.hotel.service.domain.ports.api.usecase.RegisterHotelUseCase;
import com.booking.system.hotel.service.domain.ports.api.usecase.SearchHotelAvailableUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelApplicationServiceImpl implements HotelApplicationService {

    RegisterHotelUseCase registerHotelUseCase;
    SearchHotelAvailableUseCase searchHotelAvailableUseCase;

    public HotelApplicationServiceImpl(RegisterHotelUseCase registerHotelUseCase,
                                       SearchHotelAvailableUseCase searchHotelAvailableUseCase) {
        this.registerHotelUseCase = registerHotelUseCase;
        this.searchHotelAvailableUseCase = searchHotelAvailableUseCase;
    }

    @Override
    public RegisterHotelOutput register(RegisterHotelInput input) {
        return registerHotelUseCase.execute(input);
    }

    @Override
    public List<SearchHotelAvailableOutput> searchHotelAvailableBy(SearchHotelAvailableInput input) {
        return searchHotelAvailableUseCase.execute(input);
    }


}
