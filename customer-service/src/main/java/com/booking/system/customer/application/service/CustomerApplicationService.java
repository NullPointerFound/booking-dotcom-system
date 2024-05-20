package com.booking.system.customer.application.service;

import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;

public interface CustomerApplicationService {
    ReservationOrderDetailOutput getCustomerReservationOrderDetail(String customerId, String reservationOrderId);
}
