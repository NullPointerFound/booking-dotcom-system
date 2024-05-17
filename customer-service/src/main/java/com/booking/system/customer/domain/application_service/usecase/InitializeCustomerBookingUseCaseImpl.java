package com.booking.system.customer.domain.application_service.usecase;

import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.core.exception.CustomerNotFoundException;
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

    @Override
    public void execute(final InitializeReservationOrderInput input) {
        final var reservationOrder = this.customerUseCaseMapper.initializeReservationOrderInputToReservationOrder(input);
        reservationOrder.initialize();
        this.ensureCustomerExists(reservationOrder);
        this.reservationOrderRepository.save(reservationOrder);
    }

    private void ensureCustomerExists(final ReservationOrder reservationOrder) {
        if (!this.customerRepository.customerExistsBy(reservationOrder.getCustomerId())) {
            throw new CustomerNotFoundException();
        }
    }

}

