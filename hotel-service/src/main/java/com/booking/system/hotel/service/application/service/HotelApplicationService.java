package com.booking.system.hotel.service.application.service;

import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;

import java.util.List;

public interface HotelApplicationService {

    RegisterHotelOutput register(RegisterHotelInput input);

    List<SearchHotelAvailableOutput> searchHotelAvailableBy(SearchHotelAvailableInput input);

}
