package com.booking.system.customer.domain.ports.api.usecase;

import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingFailureStatusInput;

@FunctionalInterface
public interface UpdateCustomerBookingFailureStatusUseCase {

    void execute(UpdateCustomerBookingFailureStatusInput input);

}
