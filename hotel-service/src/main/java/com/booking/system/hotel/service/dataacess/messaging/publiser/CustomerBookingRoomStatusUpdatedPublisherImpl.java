package com.booking.system.hotel.service.dataacess.messaging.publiser;

import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;
import com.booking.system.hotel.service.application.configuration.properties.ExchangeProperties;
import com.booking.system.hotel.service.application.configuration.properties.RoutingKeyProperties;
import com.booking.system.hotel.service.domain.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerBookingRoomStatusUpdatedPublisherImpl implements CustomerBookingRoomStatusUpdatedPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RoutingKeyProperties routingKeyProperties;
    private final ExchangeProperties exchangeProperties;

    @Override
    public void publish(final CustomerBookingStatusUpdatedEvent event) {
        try {
            log.info(
                    "Publishing event: {} to exchange={} | routingKey={}",
                    event,
                    this.exchangeProperties.customerBooking(),
                    this.routingKeyProperties.customerBookingUpdate()
            );
            this.rabbitTemplate.convertAndSend(
                    this.exchangeProperties.customerBooking(),
                    this.routingKeyProperties.customerBookingUpdate(),
                    event
            );
        } catch (final Exception exception) {
            log.error(
                    "Failed to publish event: {} to exchange={} | routingKey={}",
                    event,
                    this.exchangeProperties.customerBooking(),
                    this.routingKeyProperties.customerBookingUpdate(),
                    exception
            );
        }

    }
}
