package com.booking.system.hotel.service.domain.ports.api.mapper;


import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;
import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.ports.spi.queries.SearchHotelAvailableQueryResult;

public interface HotelUseCaseMapper {

    Hotel registerHotelInputToHotel(RegisterHotelInput input);

    RegisterHotelOutput hotelToRegisterHotelOutput(Hotel hotel);

    SearchHotelAvailableOutput searchHotelAvailableQueryResultToSearchHotelAvailableOutput(
            SearchHotelAvailableQueryResult queryResult
    );

}
