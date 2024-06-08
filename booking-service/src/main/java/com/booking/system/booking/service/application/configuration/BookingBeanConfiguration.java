package com.booking.system.booking.service.application.configuration;

import com.booking.system.booking.service.domain.application_service.mapper.BookingUseCaseMapperImpl;
import com.booking.system.booking.service.domain.application_service.messaging.BookingRoomRequestedHandlerImpl;
import com.booking.system.booking.service.domain.application_service.messaging.BookingRoomStatusChangedHandlerImpl;
import com.booking.system.booking.service.domain.application_service.service.BookingInitializer;
import com.booking.system.booking.service.domain.application_service.service.VerifyRoomAvailability;
import com.booking.system.booking.service.domain.application_service.usecase.BookingRoomUseCaseImpl;
import com.booking.system.booking.service.domain.application_service.usecase.UpdateBookingStatusUseCaseImpl;
import com.booking.system.booking.service.domain.ports.api.mapper.BookingUseCaseMapper;
import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomRequestedHandler;
import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.booking.system.booking.service.domain.ports.api.usecase.BookingRoomUseCase;
import com.booking.system.booking.service.domain.ports.api.usecase.UpdateBookingStatusUseCase;
import com.booking.system.booking.service.domain.ports.spi.messaging.publiser.BookingRoomResponsePublisher;
import com.booking.system.booking.service.domain.ports.spi.repository.BookingRepository;
import com.booking.system.booking.service.domain.ports.spi.repository.RoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingBeanConfiguration {

    @Bean
    public BookingRoomRequestedHandler bookingRoomRequestedHandler(
            final BookingRoomUseCase bookingRoomUseCase,
            final BookingUseCaseMapper bookingUseCaseMapper,
            final BookingRoomResponsePublisher bookingRoomResponsePublisher
    ) {
        return new BookingRoomRequestedHandlerImpl(
                bookingRoomUseCase,
                bookingUseCaseMapper,
                bookingRoomResponsePublisher
        );
    }

    @Bean
    public BookingRoomUseCase bookingRoomUseCase(
            final BookingRepository bookingRepository,
            final BookingUseCaseMapper bookingUseCaseMapper,
            final BookingInitializer bookingInitializer,
            final VerifyRoomAvailability verifyRoomAvailability
    ) {
        return new BookingRoomUseCaseImpl(
                bookingRepository,
                bookingUseCaseMapper,
                verifyRoomAvailability,
                bookingInitializer
        );
    }

    @Bean
    public VerifyRoomAvailability verifyRoomAvailability(
            final BookingRepository bookingRepository,
            final RoomRepository roomRepository
    ) {
        return new VerifyRoomAvailability(bookingRepository, roomRepository);
    }


    @Bean
    public BookingInitializer bookingInitializer() {
        return new BookingInitializer();
    }

    @Bean
    public BookingUseCaseMapper bookingRoomUseCaseMapper() {
        return new BookingUseCaseMapperImpl();
    }

    @Bean
    public BookingRoomStatusChangedHandler bookingRoomStatusChangedHandler(
            final BookingUseCaseMapper bookingUseCaseMapper,
            final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase,
            final BookingRoomResponsePublisher bookingRoomResponsePublisher
    ) {
        return new BookingRoomStatusChangedHandlerImpl(bookingUseCaseMapper, updateBookingRoomStatusUseCase, bookingRoomResponsePublisher);
    }

    @Bean
    public UpdateBookingStatusUseCase updateBookingRoomStatusUseCase(final BookingRepository bookingRepository) {
        return new UpdateBookingStatusUseCaseImpl(bookingRepository);
    }

}
