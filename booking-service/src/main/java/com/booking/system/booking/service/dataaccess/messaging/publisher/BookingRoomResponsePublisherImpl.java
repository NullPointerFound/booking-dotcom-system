package com.booking.system.booking.service.dataaccess.messaging.publisher;

import com.booking.system.booking.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.booking.service.application.configuration.properties.RoutingKeyProperties;
import com.booking.system.booking.service.domain.ports.spi.messaging.publiser.BookingRoomResponsePublisher;
import com.booking.system.commons.domain.core.event.BookingRoomResponseEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomResponsePublisherImpl implements BookingRoomResponsePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RoutingKeyProperties routingKeyProperties;
    private final ExchangeProperties exchangeProperties;

    @Override
    public void publish(final BookingRoomResponseEvent event) {
        try {
            log.info(
                    "Publishing event: {} to exchange: {} | routingKey={}",
                    event,
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomConfirmation()
            );
            this.rabbitTemplate.convertAndSend(
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomConfirmation(),
                    event
            );
        } catch (final Exception exception) {
            log.error(
                    "Failed to publish event: {} to exchange={} | routingKey={}",
                    event,
                    this.exchangeProperties.bookingRoom(),
                    this.routingKeyProperties.bookingRoomConfirmation()
            );
        }
    }

}