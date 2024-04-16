package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.ports.mapper.HotelUseCaseMapper;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.repository.LocalityRepository;
import com.booking.system.hotel.service.domain.ports.usecase.RegisterHotelUseCase;

public class RegisterHotelUseCaseImpl implements RegisterHotelUseCase {

    private final HotelRepository hotelRepository;

    private final HotelUseCaseMapper hotelUseCaseMapper;

    public RegisterHotelUseCaseImpl(HotelRepository hotelRepository, HotelUseCaseMapper hotelUseCaseMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelUseCaseMapper = hotelUseCaseMapper;
    }

    @Override
    public RegisterHotelOutput execute(RegisterHotelInput input) {
        final var hotel = this.hotelUseCaseMapper.registerHotelInputToHotel(input);
        //Todo: Validate categoryId
        //TODO: Validate locality
        hotel.initialize();
        hotel.validate();
        this.hotelRepository.register(hotel);
        return this.hotelUseCaseMapper.hotelToRegisterHotelOutput(hotel);
    }
}
