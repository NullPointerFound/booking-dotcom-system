package com.booking.system.hotel.service.domain.ports.api.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;

import java.util.List;

public interface SearchHotelAvailableUseCase {

    List<SearchHotelAvailableOutput> execute(SearchHotelAvailableInput input);

}
