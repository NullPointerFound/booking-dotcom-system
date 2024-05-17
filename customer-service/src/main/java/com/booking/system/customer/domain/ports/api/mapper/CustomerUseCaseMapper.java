package com.booking.system.customer.domain.ports.api.mapper;

import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.core.entity.ReservationOrder;

public interface CustomerUseCaseMapper {

    ReservationOrder initializeReservationOrderInputToReservationOrder(InitializeReservationOrderInput input);

}
