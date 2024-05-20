package com.booking.system.customer.dataaccess.db.mapper;

import com.booking.system.customer.dataaccess.db.entity.CustomerEntity;
import com.booking.system.customer.dataaccess.db.entity.ReservationOrderEntity;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.core.entity.ReservationOrder;

public interface CustomerDatabaseMapper {

    ReservationOrderEntity reservationOrderToReservationOrderEntity(ReservationOrder reservationOrder, CustomerEntity customerEntity);

    ReservationOrder reservationOrderEntityToReservationOrder(ReservationOrderEntity entity);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
