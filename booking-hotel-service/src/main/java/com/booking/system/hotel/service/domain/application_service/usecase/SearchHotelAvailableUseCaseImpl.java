package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableOutput;
import com.booking.system.hotel.service.domain.ports.mapper.HotelUseCaseMapper;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.usecase.SearchHotelAvailableUseCase;

import java.util.List;
import java.util.stream.Collectors;



public abstract class SearchHotelAvailableUseCaseImpl implements SearchHotelAvailableUseCase {

    private final HotelRepository hotelRepository;
    private final HotelUseCaseMapper hotelUseCaseMapper;

    public SearchHotelAvailableUseCaseImpl(
            final HotelRepository hotelRepository,
            final HotelUseCaseMapper hotelUseCaseMapper
    ) {
        this.hotelRepository = hotelRepository;
        this.hotelUseCaseMapper = hotelUseCaseMapper;
    }


    @Override
    public List<SearchHotelAvailableOutput> execute(final SearchHotelAvailableInput input) {
        final var queryResult = this.hotelRepository.searchHotelAvailableBy(
                input.name(),
                input.category(),
                input.city(),
                input.state()
        );

        return queryResult.stream()
                .map(this.hotelUseCaseMapper::searchHotelAvailableQueryResultToSearchHotelAvailableOutput)
                .collect(Collectors.toList());
    }
}
