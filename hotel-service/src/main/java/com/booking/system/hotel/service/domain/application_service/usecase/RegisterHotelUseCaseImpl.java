package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.commons.message.ApplicationMessage;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.core.exception.HotelDomainException;
import com.booking.system.hotel.service.domain.ports.mapper.HotelUseCaseMapper;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.repository.LocalityRepository;
import com.booking.system.hotel.service.domain.ports.usecase.RegisterHotelUseCase;

public class RegisterHotelUseCaseImpl implements RegisterHotelUseCase {

    private final HotelRepository hotelRepository;
    private final LocalityRepository localityRepository;
    private final HotelUseCaseMapper hotelUseCaseMapper;

    public RegisterHotelUseCaseImpl(HotelRepository hotelRepository, LocalityRepository localityRepository, HotelUseCaseMapper hotelUseCaseMapper) {
        this.hotelRepository = hotelRepository;
        this.localityRepository = localityRepository;
        this.hotelUseCaseMapper = hotelUseCaseMapper;
    }

    @Override
    public RegisterHotelOutput execute(RegisterHotelInput input) {
        final var hotel = this.hotelUseCaseMapper.registerHotelInputToHotel(input);
        this.validateCategoryId(hotel);
        this.validateLocality(hotel);
        hotel.initialize();
        hotel.validate();
        this.hotelRepository.register(hotel);
        return this.hotelUseCaseMapper.hotelToRegisterHotelOutput(hotel);
    }


    private void validateCategoryId(final Hotel hotel) {
        if (!this.hotelRepository.existsCategoryById(hotel.getCategoryId())) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_CATEGORY_NOT_FOUND);
        }
    }

    private void validateLocality(final Hotel hotel) {
        if (!this.localityRepository.existsLocalityById(hotel.getLocalityId())) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_LOCALITY_NOT_FOUND);
        }
    }
}
