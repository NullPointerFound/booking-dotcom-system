package com.booking.system.customer.domain.ports.api.usecase;

import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;

@FunctionalInterface
public interface InitializeCustomerBookingUseCase {

    void execute(InitializeReservationOrderInput input);
}
