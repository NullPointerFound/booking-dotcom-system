package com.booking.system.customer.domain.application_service.usecase;

import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;
import com.booking.system.customer.domain.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.booking.system.customer.domain.ports.spi.repository.CustomerRepository;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;

public class InitializeCustomerBookingUseCaseImpl implements InitializeCustomerBookingUseCase {

    private final CustomerRepository customerRepository;
    private final ReservationOrderRepository reservationOrderRepository;
    private final CustomerUseCaseMapper customerUseCaseMapper;

    public InitializeCustomerBookingUseCaseImpl(final CustomerRepository customerRepository,
                                                final ReservationOrderRepository reservationOrderRepository,
                                                final CustomerUseCaseMapper customerUseCaseMapper) {
        this.customerRepository = customerRepository;
        this.reservationOrderRepository = reservationOrderRepository;
        this.customerUseCaseMapper = customerUseCaseMapper;
    }

    //TODO: NEED TO BE IMPLEMENTED
    @Override
    public void execute(InitializeReservationOrderInput input) {

    }
}
