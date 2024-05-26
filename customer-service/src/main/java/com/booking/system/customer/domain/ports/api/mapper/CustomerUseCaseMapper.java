package com.booking.system.customer.domain.ports.api.mapper;

import com.booking.system.commons.domain.core.event.CustomerBookingInitiatedEvent;
import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;
import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;
import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingStatusInput;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.core.entity.ReservationOrder;

public interface CustomerUseCaseMapper {

    ReservationOrder initializeReservationOrderInputToReservationOrder(InitializeReservationOrderInput input);

    ReservationOrderDetailOutput reservationOrderToReservationOrderDetailOutput(ReservationOrder reservationOrder, Customer customer);

    InitializeReservationOrderInput customerBookingInitiatedEventToInitializeCustomerBookingInput(CustomerBookingInitiatedEvent event);

    UpdateCustomerBookingStatusInput customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(CustomerBookingStatusUpdatedEvent event);


}
