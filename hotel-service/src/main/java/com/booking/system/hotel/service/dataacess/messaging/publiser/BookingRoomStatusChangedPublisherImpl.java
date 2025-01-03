package com.booking.system.hotel.service.dataacess.messaging.publiser;

import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;
import com.booking.system.hotel.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.hotel.service.application.configuration.properties.RoutingKeyProperties;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.BookingRoomStatusChangedPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomStatusChangedPublisherImpl implements BookingRoomStatusChangedPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RoutingKeyProperties routingKeyProperties;
    private final ExchangeProperties exchangeProperties;

    @Override
    public void publish(final BookingRoomStatusUpdatedEvent event) {
        try {
            log.info(
                    "Publishing event: {} to exchange={} | routingKey={}",
                    event,
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomStatusChanged()
            );
            this.rabbitTemplate.convertAndSend(
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomStatusChanged(),
                    event
            );
        } catch (final Exception exception) {
            log.error(
                    "Failed to publish event: {} to exchange={} | routingKey={}",
                    event,
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomStatusChanged(),
                    exception
            );
        }
    }

}
