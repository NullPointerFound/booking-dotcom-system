package com.booking.system.hotel.service.domain.application_service.messaging;

import com.booking.system.commons.domain.core.event.*;
import com.booking.system.commons.domain.core.valueobject.BookingStatus;
import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import com.booking.system.hotel.service.domain.ports.api.messaging.PaymentResponseHandler;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.BookingRoomStatusChangedPublisher;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentResponseHandlerImpl implements PaymentResponseHandler {

    private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomStatusUpdatedPublisher;

    private final BookingRoomStatusChangedPublisher bookingRoomStatusChangedPublisher;

    public PaymentResponseHandlerImpl(
            final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomStatusUpdatedPublisher,
            final BookingRoomStatusChangedPublisher bookingRoomStatusChangedPublisher
    ) {
        this.customerBookingRoomStatusUpdatedPublisher = customerBookingRoomStatusUpdatedPublisher;
        this.bookingRoomStatusChangedPublisher = bookingRoomStatusChangedPublisher;
    }

    @Override
    public void handle(final PaymentResponseEvent event) {
        switch (event) {
            case final PaymentCompletedEvent e -> {
                log.info(
                        "Payment completed, notifying customer service & booking service | reservationOrderId={}",
                        e.getReservationOrderId()
                );
                final var customerBookingPaymentCompletedEvent = this.paymentCompletedEventToCustomerBookingPaymentCompletedEvent(e);
                this.customerBookingRoomStatusUpdatedPublisher.publish(customerBookingPaymentCompletedEvent);
                log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

                final var bookingRoomPaymentCompleted = this.paymentCompletedEventToBookingRoomPaymentCompleted(e);
                this.bookingRoomStatusChangedPublisher.publish(bookingRoomPaymentCompleted);
                log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
            }
            case final PaymentFailedEvent e -> {
                log.info(
                        "Payment failed with error messages \"{}\". Notifying customer service & booking service | reservationOrderId={}",
                        String.join(", ", e.getFailureMessages()),
                        e.getReservationOrderId()
                );

                final var customerBookingPaymentFailedEvent = this.paymentFailedEventToCustomerBookingPaymentFailedEvent(e);
                this.customerBookingRoomStatusUpdatedPublisher.publish(customerBookingPaymentFailedEvent);
                log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

                final var bookingRoomPaymentFailedEvent = this.paymentFailedEventToBookingRoomPaymentFailedEvent(e);
                this.bookingRoomStatusChangedPublisher.publish(bookingRoomPaymentFailedEvent);
                log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
            }
            default -> throw new IllegalStateException(
                    "Failed on handling sub-type of PaymentResponseEvent: Unknown event"
            );
        }
    }

    private BookingRoomPaymentCompleted paymentCompletedEventToBookingRoomPaymentCompleted(final PaymentCompletedEvent e) {
        return BookingRoomPaymentCompleted.builder()
                .reservationOrderId(e.getReservationOrderId())
                .status(BookingStatus.CONFIRMED)
                .customerId(e.getCustomerId())
                .build();
    }

    private BookingRoomPaymentFailed paymentFailedEventToBookingRoomPaymentFailedEvent(final PaymentFailedEvent e) {
        return BookingRoomPaymentFailed.builder()
                .reservationOrderId(e.getReservationOrderId())
                .status(BookingStatus.CANCELED)
                .customerId(e.getCustomerId())
                .failureMessages(e.getFailureMessages())
                .build();
    }

    private CustomerBookingPaymentFailedEvent paymentFailedEventToCustomerBookingPaymentFailedEvent(final PaymentFailedEvent event) {
        return CustomerBookingPaymentFailedEvent.builder()
                .reservationOrderId(event.getReservationOrderId())
                .status(CustomerReservationStatus.PAYMENT_FAILED)
                .customerId(event.getCustomerId())
                .failureMessages(event.getFailureMessages())
                .build();
    }

    private CustomerBookingPaymentCompletedEvent paymentCompletedEventToCustomerBookingPaymentCompletedEvent(final PaymentCompletedEvent event) {
        return CustomerBookingPaymentCompletedEvent.builder()
                .reservationOrderId(event.getReservationOrderId())
                .customerId(event.getCustomerId())
                .status(CustomerReservationStatus.PAYMENT_CONFIRMED)
                .build();
    }

}
