package com.booking.system.hotel.service.application.service;

import com.booking.system.hotel.service.domain.application_service.dto.*;

import java.util.List;

public interface HotelApplicationService {

    RegisterHotelOutput register(RegisterHotelInput input);

    List<SearchHotelAvailableOutput> searchHotelAvailableBy(SearchHotelAvailableInput input);

    BookingRoomOutput bookingRoomRequest(BookingRoomInput input);


}
