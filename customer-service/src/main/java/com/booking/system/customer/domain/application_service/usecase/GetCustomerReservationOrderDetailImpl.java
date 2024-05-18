package com.booking.system.customer.domain.application_service.usecase;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailInput;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;
import com.booking.system.customer.domain.ports.api.usecase.GetCustomerReservationOrderDetail;
import com.booking.system.customer.domain.ports.spi.repository.CustomerRepository;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;

public class GetCustomerReservationOrderDetailImpl implements GetCustomerReservationOrderDetail {

    private final CustomerRepository customerRepository;
    private final ReservationOrderRepository reservationOrderRepository;
    private final CustomerUseCaseMapper customerUseCaseMapper;

    public GetCustomerReservationOrderDetailImpl(
            final CustomerRepository customerRepository,
            final ReservationOrderRepository reservationOrderRepository,
            final CustomerUseCaseMapper customerUseCaseMapper
    ) {
        this.customerRepository = customerRepository;
        this.reservationOrderRepository = reservationOrderRepository;
        this.customerUseCaseMapper = customerUseCaseMapper;
    }

    @Override
    public ReservationOrderDetailOutput execute(final ReservationOrderDetailInput input) {
        final var customer = this.customerRepository.findById(CustomerId.of(input.customerId()));
        final var reservationOrder = this.reservationOrderRepository.findById(ReservationOrderId.of(input.reservationOrderId()));
        return this.customerUseCaseMapper.reservationOrderToReservationOrderDetailOutput(reservationOrder, customer);
    }
}
