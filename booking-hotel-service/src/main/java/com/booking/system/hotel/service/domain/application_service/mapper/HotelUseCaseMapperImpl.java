package com.booking.system.hotel.service.domain.application_service.mapper;

import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.domain.entity.Hotel;
import com.booking.system.hotel.service.domain.domain.valueobject.HotelAddress;
import com.booking.system.hotel.service.domain.domain.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.domain.valueobject.LocalityId;
import com.booking.system.hotel.service.domain.ports.mapper.HotelUseCaseMapper;

public class HotelUseCaseMapperImpl implements HotelUseCaseMapper {

    @Override
    public Hotel registerHotelInputToHotel(final RegisterHotelInput input) {
        final var address = HotelAddress.builder()
                .street(input.street())
                .zip(input.zip())
                .build();
        return Hotel.builder()
                .id(null)
                .name(input.name())
                .description(input.description())
                .address(address)
                .categoryId(HotelCategoryId.of(input.categoryId()))
                .localityId(LocalityId.of(input.localityId()))
                .build();
    }

    @Override
    public RegisterHotelOutput hotelToRegisterHotelOutput(final Hotel hotel) {
        return new RegisterHotelOutput(hotel.getId().toString(), hotel.getRooms().asRawIds());
    }

}
