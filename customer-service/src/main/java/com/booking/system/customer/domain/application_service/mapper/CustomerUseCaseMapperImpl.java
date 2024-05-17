package com.booking.system.customer.domain.application_service.mapper;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;

public class CustomerUseCaseMapperImpl implements CustomerUseCaseMapper {

    @Override
    public ReservationOrder initializeReservationOrderInputToReservationOrder(final InitializeReservationOrderInput input) {
        return ReservationOrder.builder()
                .id(ReservationOrderId.of(input.reservationOrderId()))
                .customerId(CustomerId.of(input.customerId()))
                .hotelId(HotelId.of(input.hotelId()))
                .totalPrice(Money.of(input.totalPrice()))
                .guests(input.guests())
                .checkIn(input.checkIn())
                .checkOut(input.checkOut())
                .build();
    }
}
