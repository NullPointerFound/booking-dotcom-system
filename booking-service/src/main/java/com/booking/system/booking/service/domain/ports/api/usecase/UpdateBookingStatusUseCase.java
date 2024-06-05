package com.booking.system.booking.service.domain.ports.api.usecase;


import com.booking.system.booking.service.domain.application_service.dto.UpdateBookingStatusInput;
import com.booking.system.booking.service.domain.application_service.dto.UpdateBookingStatusOutput;

@FunctionalInterface
public interface UpdateBookingStatusUseCase {

    UpdateBookingStatusOutput execute(UpdateBookingStatusInput input);

}
