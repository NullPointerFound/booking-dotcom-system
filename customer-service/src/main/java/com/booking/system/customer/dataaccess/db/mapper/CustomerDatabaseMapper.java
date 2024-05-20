package com.booking.system.customer.dataaccess.db.mapper;

import com.booking.system.customer.dataaccess.db.entity.CustomerEntity;
import com.booking.system.customer.dataaccess.db.entity.ReservationOrderEntity;
import com.booking.system.customer.dataaccess.db.entity.ReservationOrderHistoryEntity;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.core.entity.ReservationOrderTimeline;

public interface CustomerDatabaseMapper {

    ReservationOrderEntity reservationOrderToReservationOrderEntity(ReservationOrder reservationOrder, CustomerEntity customerEntity);

    ReservationOrderHistoryEntity reservationOrderHistoryToReservationOrderHistoryEntity(ReservationOrderTimeline reservationOrderTimeline);

    ReservationOrder reservationOrderEntityToReservationOrder(ReservationOrderEntity entity);

    ReservationOrderTimeline reservationOrderHistoryEntityToReservationOrderTimeline(ReservationOrderHistoryEntity entity);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
