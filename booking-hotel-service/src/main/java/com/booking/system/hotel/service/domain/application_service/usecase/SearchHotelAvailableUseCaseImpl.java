package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;
import com.booking.system.hotel.service.domain.ports.usecase.SearchHotelAvailableUseCase;

import java.util.List;


public abstract class SearchHotelAvailableUseCaseImpl implements SearchHotelAvailableUseCase {

    //TODO:implementing execute
    @Override
    public List<SearchHotelAvailableOutput> execute(SearchHotelAvailableInput input) {
        return List.of();
    }
}
