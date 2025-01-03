package com.booking.system.hotel.service.application.service.impl;

import com.booking.system.hotel.service.application.service.HotelApplicationService;
import com.booking.system.hotel.service.domain.application_service.dto.*;
import com.booking.system.hotel.service.domain.ports.api.usecase.BookingRoomRequestUseCase;
import com.booking.system.hotel.service.domain.ports.api.usecase.RegisterHotelUseCase;
import com.booking.system.hotel.service.domain.ports.api.usecase.SearchHotelAvailableUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelApplicationServiceImpl implements HotelApplicationService {

    private final RegisterHotelUseCase registerHotelUseCase;
    private final SearchHotelAvailableUseCase searchHotelAvailableUseCase;
    private final BookingRoomRequestUseCase bookingRoomRequestUseCase;


    public HotelApplicationServiceImpl(RegisterHotelUseCase registerHotelUseCase,
                                       SearchHotelAvailableUseCase searchHotelAvailableUseCase,
                                       BookingRoomRequestUseCase bookingRoomRequestUseCase) {
        this.registerHotelUseCase = registerHotelUseCase;
        this.searchHotelAvailableUseCase = searchHotelAvailableUseCase;
        this.bookingRoomRequestUseCase = bookingRoomRequestUseCase;
    }

    @Override
    public RegisterHotelOutput register(RegisterHotelInput input) {
        return registerHotelUseCase.execute(input);
    }

    @Override
    public List<SearchHotelAvailableOutput> searchHotelAvailableBy(SearchHotelAvailableInput input) {
        return searchHotelAvailableUseCase.execute(input);
    }

    @Override
    public BookingRoomOutput bookingRoomRequest(final BookingRoomInput input) {
        return this.bookingRoomRequestUseCase.execute(input);
    }
}
