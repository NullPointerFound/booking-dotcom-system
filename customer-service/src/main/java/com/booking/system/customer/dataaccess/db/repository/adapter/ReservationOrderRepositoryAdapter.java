package com.booking.system.customer.dataaccess.db.repository.adapter;

import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.dataaccess.db.mapper.CustomerDatabaseMapper;
import com.booking.system.customer.dataaccess.db.repository.ReservationOrderJpaRepository;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.ports.spi.repository.ReservationOrderRepository;
import com.booking.system.customer.domain.core.exception.ReservationOrderNotFoundException;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReservationOrderRepositoryAdapter implements ReservationOrderRepository {

    private final CustomerRepositoryAdapter customerRepositoryAdapter;
    private final ReservationOrderJpaRepository repository;
    private final CustomerDatabaseMapper mapper;


    @Override
    public void save(final ReservationOrder reservationOrder) {
        final var entity = this.mapper.reservationOrderToReservationOrderEntity(
                reservationOrder,
                this.customerRepositoryAdapter.findCustomerEntityById(reservationOrder.getCustomerId())
        );
        this.repository.save(entity);
    }

    @Override
    public ReservationOrder findById(final ReservationOrderId reservationOrderId) {
        return this.repository.findById(reservationOrderId.getValue())
                .map(this.mapper::reservationOrderEntityToReservationOrder)
                .orElseThrow(ReservationOrderNotFoundException::new);
    }
}
