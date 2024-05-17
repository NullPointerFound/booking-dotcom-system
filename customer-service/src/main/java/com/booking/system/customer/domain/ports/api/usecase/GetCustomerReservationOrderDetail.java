package com.booking.system.customer.domain.ports.api.usecase;

import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailInput;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;

@FunctionalInterface
public interface GetCustomerReservationOrderDetail {

    ReservationOrderDetailOutput execute(ReservationOrderDetailInput input);

}
