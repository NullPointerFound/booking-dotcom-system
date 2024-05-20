package com.booking.system.customer.application.service.impl;

import com.booking.system.customer.application.service.CustomerApplicationService;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailInput;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;
import com.booking.system.customer.domain.ports.api.usecase.GetCustomerReservationOrderDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final GetCustomerReservationOrderDetail getCustomerReservationOrderDetail;

    @Override
    public ReservationOrderDetailOutput getCustomerReservationOrderDetail(final String customerId, final String reservationOrderId) {
        return this.getCustomerReservationOrderDetail.execute(new ReservationOrderDetailInput(customerId, reservationOrderId));
    }
}
