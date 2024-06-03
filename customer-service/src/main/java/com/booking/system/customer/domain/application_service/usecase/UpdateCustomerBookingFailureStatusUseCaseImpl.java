package com.booking.system.customer.domain.application_service.usecase;

import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingFailureStatusInput;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingFailureStatusUseCase;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;

public class UpdateCustomerBookingFailureStatusUseCaseImpl implements UpdateCustomerBookingFailureStatusUseCase {

    private final ReservationOrderRepository repository;

    public UpdateCustomerBookingFailureStatusUseCaseImpl(final ReservationOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final UpdateCustomerBookingFailureStatusInput input) {
        final var reservationOrder = this.repository.findById(ReservationOrderId.of(input.reservationOrderId()));
        reservationOrder.updateToFailureStatus(input.status(), input.failureMessages());
        this.repository.save(reservationOrder);
    }
}