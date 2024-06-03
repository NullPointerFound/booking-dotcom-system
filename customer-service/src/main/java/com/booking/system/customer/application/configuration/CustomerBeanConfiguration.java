package com.booking.system.customer.application.configuration;

import com.booking.system.customer.domain.application_service.mapper.CustomerUseCaseMapperImpl;
import com.booking.system.customer.domain.application_service.messaging.CustomerBookingStatusUpdatedHandlerImpl;
import com.booking.system.customer.domain.application_service.usecase.GetCustomerReservationOrderDetailImpl;
import com.booking.system.customer.domain.application_service.usecase.InitializeCustomerBookingUseCaseImpl;
import com.booking.system.customer.domain.application_service.usecase.UpdateCustomerBookingFailureStatusUseCaseImpl;
import com.booking.system.customer.domain.application_service.usecase.UpdateCustomerBookingStatusUseCaseImpl;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;
import com.booking.system.customer.domain.ports.api.messaging.CustomerBookingStatusUpdatedHandler;
import com.booking.system.customer.domain.ports.api.usecase.GetCustomerReservationOrderDetail;
import com.booking.system.customer.domain.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingFailureStatusUseCase;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingStatusUseCase;
import com.booking.system.customer.domain.ports.spi.repository.CustomerRepository;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeanConfiguration {

    @Bean
    public CustomerUseCaseMapper customerUseCaseMapper() {
        return new CustomerUseCaseMapperImpl();
    }

    @Bean
    public InitializeCustomerBookingUseCase initiateCustomerBookingUseCase(
            final CustomerRepository customerRepository,
            final ReservationOrderRepository reservationOrderRepository,
            final CustomerUseCaseMapper customerUseCaseMapper
    ) {
        return new InitializeCustomerBookingUseCaseImpl(customerRepository, reservationOrderRepository, customerUseCaseMapper);
    }

    @Bean
    public UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase(
            final ReservationOrderRepository reservationOrderRepository
    ) {
        return new UpdateCustomerBookingStatusUseCaseImpl(reservationOrderRepository);
    }

    @Bean
    public GetCustomerReservationOrderDetail getCustomerReservationOrderDetail(
            final CustomerRepository customerRepository,
            final ReservationOrderRepository reservationOrderRepository,
            final CustomerUseCaseMapper customerUseCaseMapper
    ) {
        return new GetCustomerReservationOrderDetailImpl(customerRepository, reservationOrderRepository, customerUseCaseMapper);
    }

    @Bean
    public CustomerBookingStatusUpdatedHandler customerBookingStatusUpdatedHandler(
            final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase,
            final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase,
            final UpdateCustomerBookingFailureStatusUseCase updateCustomerBookingFailureStatusUseCase,
            final CustomerUseCaseMapper customerUseCaseMapper
    ) {
        return new CustomerBookingStatusUpdatedHandlerImpl(
                initializeCustomerBookingUseCase,
                updateCustomerBookingStatusUseCase,
                customerUseCaseMapper,
                updateCustomerBookingFailureStatusUseCase

        );
    }

    @Bean
    public UpdateCustomerBookingFailureStatusUseCase updateCustomerBookingFailureStatusUseCase(final ReservationOrderRepository reservationOrderRepository) {
        return new UpdateCustomerBookingFailureStatusUseCaseImpl(reservationOrderRepository);
    }
}
