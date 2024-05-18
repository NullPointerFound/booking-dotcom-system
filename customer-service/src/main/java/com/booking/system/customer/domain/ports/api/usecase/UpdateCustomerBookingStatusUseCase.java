package com.booking.system.customer.domain.ports.api.usecase;

import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingStatusInput;

@FunctionalInterface
public interface UpdateCustomerBookingStatusUseCase {

    void execute(UpdateCustomerBookingStatusInput input);

}
