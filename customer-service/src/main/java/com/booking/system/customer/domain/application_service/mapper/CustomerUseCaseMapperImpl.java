package com.booking.system.customer.domain.application_service.mapper;

import com.booking.system.commons.domain.core.event.CustomerBookingInitiatedEvent;
import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;
import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.domain.core.valueobject.Money;
import com.booking.system.commons.domain.core.valueobject.ReservationOrderId;
import com.booking.system.customer.domain.application_service.dto.InitializeReservationOrderInput;
import com.booking.system.customer.domain.application_service.dto.ReservationOrderDetailOutput;
import com.booking.system.customer.domain.application_service.dto.TimelineItem;
import com.booking.system.customer.domain.application_service.dto.UpdateCustomerBookingStatusInput;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.core.entity.ReservationOrder;
import com.booking.system.customer.domain.core.entity.ReservationOrderTimeline;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;

import java.time.ZoneId;

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

    @Override
    public ReservationOrderDetailOutput reservationOrderToReservationOrderDetailOutput(
            final ReservationOrder reservationOrder,
            final Customer customer
    ) {
        return ReservationOrderDetailOutput.builder()
                .customerId(customer.getId().toString())
                .customerEmail(customer.getEmail().value())
                .customerName(customer.getName())
                .reservationOrderId(reservationOrder.getId().toString())
                .hotelId(reservationOrder.getHotelId().toString())
                .totalPrice(reservationOrder.getTotalPrice().getValue())
                .checkIn(reservationOrder.getCheckIn())
                .checkOut(reservationOrder.getCheckOut())
                .status(reservationOrder.getCurrentStatus())
                .timeline(reservationOrder.getTimeline().mapToListOf(this::reservationOrderTimelineToTimelineItem))
                .build();
    }

    @Override
    public InitializeReservationOrderInput customerBookingInitiatedEventToInitializeCustomerBookingInput(final CustomerBookingInitiatedEvent event) {
        return new InitializeReservationOrderInput(
                event.getReservationOrderId(),
                event.getCustomerId(),
                event.getHotelId(),
                event.getTotalPrice(),
                event.getGuests(),
                event.getCheckIn(),
                event.getCheckOut(),
                event.getStatus()
        );
    }

    @Override
    public UpdateCustomerBookingStatusInput customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(
            final CustomerBookingStatusUpdatedEvent event
    ) {
        return new UpdateCustomerBookingStatusInput(
                event.getCustomerId(),
                event.getReservationOrderId(),
                event.getStatus()
        );
    }
    private TimelineItem reservationOrderTimelineToTimelineItem(final ReservationOrderTimeline entity) {
        return new TimelineItem(
                entity.getStatus(),
                entity.getOccurredAt()
                        .atZone(ZoneId.of("America/New_York"))
                        .toLocalDateTime(),
                entity.getReason()
        );
    }

}
