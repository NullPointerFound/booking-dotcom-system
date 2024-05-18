package com.booking.system.customer.domain.application_service.usecase;

import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingStatusInput;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingStatusUseCase;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;

public class UpdateCustomerBookingStatusUseCaseImpl implements UpdateCustomerBookingStatusUseCase {

    private final ReservationOrderRepository repository;

    public UpdateCustomerBookingStatusUseCaseImpl(final ReservationOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final UpdateCustomerBookingStatusInput input) {
        final var reservationOrder = this.repository.findById(ReservationOrderId.of(input.reservationOrderId()));
        reservationOrder.updateStatus(input.status());
        this.repository.save(reservationOrder);
    }
}