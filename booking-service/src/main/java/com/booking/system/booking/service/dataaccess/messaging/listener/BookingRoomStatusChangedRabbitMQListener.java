package com.booking.system.booking.service.dataaccess.messaging.listener;

import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.booking.system.booking.service.domain.ports.spi.messaging.listener.BookingRoomStatusChangedListener;
import com.booking.system.commons.domain.core.event.BookingRoomStatusUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomStatusChangedRabbitMQListener implements BookingRoomStatusChangedListener {

    private final BookingRoomStatusChangedHandler handler;

    @Override
    @RabbitListener(queues = "${app.rabbitmq.queue.booking-room-status-changed}")
    public void listen(final BookingRoomStatusUpdatedEvent event) {
        log.info("BookingRoomStatusUpdatedEvent received: {}, ", event);
        this.handler.handle(event);
    }
}
