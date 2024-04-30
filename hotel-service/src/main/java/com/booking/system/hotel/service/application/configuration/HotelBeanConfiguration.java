package com.booking.system.hotel.service.application.configuration;

import com.booking.system.hotel.service.domain.application_service.mapper.HotelUseCaseMapperImpl;
import com.booking.system.hotel.service.domain.application_service.usecase.RegisterHotelUseCaseImpl;
import com.booking.system.hotel.service.domain.application_service.usecase.SearchHotelAvailableUseCaseImpl;
import com.booking.system.hotel.service.domain.ports.api.mapper.HotelUseCaseMapper;
import com.booking.system.hotel.service.domain.ports.spi.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.spi.repository.LocalityRepository;
import com.booking.system.hotel.service.domain.ports.api.usecase.RegisterHotelUseCase;
import com.booking.system.hotel.service.domain.ports.api.usecase.SearchHotelAvailableUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelBeanConfiguration {

    @Bean
    public RegisterHotelUseCase registerHotelUseCase(
            final HotelRepository hotelRepository,
            final LocalityRepository localityRepository,
            final HotelUseCaseMapper hotelUseCaseMapper
    ) {
        return new RegisterHotelUseCaseImpl(hotelRepository, localityRepository, hotelUseCaseMapper);
    }

    @Bean
    public HotelUseCaseMapper hotelUseCaseMapper() {
        return new HotelUseCaseMapperImpl();
    }


    @Bean
    public SearchHotelAvailableUseCase searchHotelAvailableUseCase(final HotelRepository hotelRepository,
                                                                   final HotelUseCaseMapper hotelUseCaseMapper){
        return new SearchHotelAvailableUseCaseImpl(hotelRepository,hotelUseCaseMapper);
    }


}
