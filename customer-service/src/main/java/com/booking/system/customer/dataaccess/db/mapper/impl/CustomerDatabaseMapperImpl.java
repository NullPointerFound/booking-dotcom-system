package com.booking.system.customer.dataaccess.db.mapper.impl;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.dataaccess.db.entity.CustomerEntity;
import com.booking.system.customer.dataaccess.db.entity.ReservationOrderEntity;
import com.booking.system.customer.dataaccess.db.entity.ReservationOrderHistoryEntity;
import com.booking.system.customer.dataaccess.db.mapper.CustomerDatabaseMapper;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.core.entity.ReservationOrderTimeline;
import com.booking.system.customer.domain.core.valueobject.Email;
import com.booking.system.customer.domain.core.valueobject.ReservationOrderTimelineId;
import com.booking.system.customer.domain.core.valueobject.Timeline;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerDatabaseMapperImpl implements CustomerDatabaseMapper {

    @Override
    public ReservationOrderEntity reservationOrderToReservationOrderEntity(
            final ReservationOrder reservationOrder,
            final CustomerEntity customerEntity
    ) {
        final var entity = ReservationOrderEntity.builder()
                .id(reservationOrder.getId().getValue())
                .customer(customerEntity)
                .hotelId(reservationOrder.getHotelId().getValue())
                .guests(reservationOrder.getGuests())
                .checkIn(reservationOrder.getCheckIn())
                .checkOut(reservationOrder.getCheckOut())
                .totalPrice(reservationOrder.getTotalPrice().getValue())
                .currentStatus(reservationOrder.getCurrentStatus())
                .history(reservationOrder.getTimeline().stream()
                        .map(this::reservationOrderHistoryToReservationOrderHistoryEntity)
                        .collect(Collectors.toSet()))
                .build();
        entity.getHistory().forEach(history -> history.setReservationOrder(entity));
        return entity;
    }

    @Override
    public ReservationOrderHistoryEntity reservationOrderHistoryToReservationOrderHistoryEntity(final ReservationOrderTimeline reservationOrderTimeline) {
        return ReservationOrderHistoryEntity.builder()
                .id(reservationOrderTimeline.getId().getValue())
                .failureReason(reservationOrderTimeline.getReason())
                .status(reservationOrderTimeline.getStatus())
                .occurredAt(reservationOrderTimeline.getOccurredAt())
                .build();
    }

    @Override
    public ReservationOrder reservationOrderEntityToReservationOrder(final ReservationOrderEntity entity) {
        return ReservationOrder.builder()
                .id(ReservationOrderId.of(entity.getId()))
                .customerId(CustomerId.of(entity.getCustomer().getId()))
                .hotelId(HotelId.of(entity.getHotelId()))
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .guests(entity.getGuests())
                .totalPrice(Money.of(entity.getTotalPrice()))
                .currentStatus(entity.getCurrentStatus())
                .timeline(Timeline.of(
                        entity.getHistory().stream()
                                .map(this::reservationOrderHistoryEntityToReservationOrderTimeline)
                                .collect(Collectors.toList())
                ))
                .build();
    }

    @Override
    public ReservationOrderTimeline reservationOrderHistoryEntityToReservationOrderTimeline(final ReservationOrderHistoryEntity entity) {
        return ReservationOrderTimeline.builder()
                .id(ReservationOrderTimelineId.of(entity.getId()))
                .status(entity.getStatus())
                .occurredAt(entity.getOccurredAt())
                .reason(entity.getFailureReason())
                .build();
    }

    @Override
    public Customer customerEntityToCustomer(final CustomerEntity customerEntity) {
        return Customer.builder()
                .id(CustomerId.of(customerEntity.getId()))
                .email(new Email(customerEntity.getEmail()))
                .name(customerEntity.getName())
                .build();
    }
}
