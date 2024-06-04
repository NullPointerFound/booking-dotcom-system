package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.*;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.hotel.service.domain.ports.api.messaging.BookingRoomResponseHandler;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.PaymentRequestedPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomResponseHandlerImpl implements BookingRoomResponseHandler {


    private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatedPublisher;
    private final PaymentRequestedPublisher paymentRequestedPublisher;

    public BookingRoomResponseHandlerImpl(
            final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatedPublisher,
            final PaymentRequestedPublisher paymentRequestedPublisher
    ) {
        this.customerBookingRoomUpdatedPublisher = customerBookingRoomUpdatedPublisher;
        this.paymentRequestedPublisher = paymentRequestedPublisher;
    }

    @Override
    public void handle(BookingRoomResponseEvent event) {
        switch (event) {
            case final BookingRoomPendingEvent e -> {

                log.info("Booking room pending, notifying customer service | reservationOrderId={}", e.getReservationOrderId());
                final var bookingRoomStatusUpdatedEvent = this.bookingRoomPendingEventToCustomerBookingPaymentRequested(e);
                this.customerBookingRoomUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
                log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

                log.info("Booking room pending, requesting for payment | reservationOrderId={}", e.getReservationOrderId());
                final var paymentRequestedEvent = this.bookingRoomPendingEventToPaymentRequestedEvent(e);
                this.paymentRequestedPublisher.publish(paymentRequestedEvent);
                log.info("Payment service notified | reservationOrderId={}", e.getReservationOrderId());
            }
            case final BookingRoomConfirmedEvent e -> {
                log.info("Booking room confirmed, notifying customer service | reservationOrderId={}", e.getReservationOrderId());
                final var bookingRoomStatusUpdatedEvent = this.bookingRoomConfirmedEventToCustomerBookingCompletedEvent(e);
                this.customerBookingRoomUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
                log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());
            }
            case final BookingRoomFailedEvent e -> {
                log.info(
                        "Booking room failed with error messages \"{}\". Notifying customer service | reservationOrderId={}",
                        String.join(", ", e.getFailureMessages()),
                        e.getReservationOrderId()
                );
                final var bookingRoomStatusUpdatedEvent = this.bookingRoomFailedEventToCustomerBookingRejectedEvent(e);
                this.customerBookingRoomUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
                log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());
            }
            default -> throw new IllegalStateException(
                    "Failed on handling sub-type of BookingRoomResponseEvent: Unknown event"
            );
        }
    }


    private CustomerBookingStatusUpdatedEvent bookingRoomPendingEventToCustomerBookingPaymentRequested(final BookingRoomPendingEvent event) {
        return CustomerBookingPaymentRequestedEvent.builder()
                .bookingRoomId(event.getBookingRoomId())
                .reservationOrderId(event.getReservationOrderId())
                .customerId(event.getCustomerId())
                .status(CustomerReservationStatus.AWAITING_PAYMENT)
                .build();
    }

    private PaymentRequestedEvent bookingRoomPendingEventToPaymentRequestedEvent(final BookingRoomPendingEvent event) {
        return PaymentRequestedEvent.builder()
                .reservationOrderId(event.getReservationOrderId())
                .bookingRoomId(event.getBookingRoomId())
                .customerId(event.getCustomerId())
                .totalPrice(event.getTotalPrice())
                .build();
    }

    private CustomerBookingCompletedEvent bookingRoomConfirmedEventToCustomerBookingCompletedEvent(final BookingRoomConfirmedEvent event) {
        return CustomerBookingCompletedEvent.builder()
                .reservationOrderId(event.getReservationOrderId())
                .customerId(event.getCustomerId())
                .status(CustomerReservationStatus.RESERVED)
                .build();
    }

    private CustomerBookingStatusUpdatedEvent bookingRoomFailedEventToCustomerBookingRejectedEvent(final BookingRoomFailedEvent event) {
        return CustomerBookingRejectedEvent.builder()
                .reservationOrderId(event.getReservationOrderId())
                .failureMessages(event.getFailureMessages())
                .customerId(event.getCustomerId())
                .status(CustomerReservationStatus.RESERVATION_FAILED)
                .build();
    }
}
