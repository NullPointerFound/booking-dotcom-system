package com.booking.system.booking.service.dataaccess.messaging.listener;

import com.booking.system.booking.service.domain.ports.api.messaging.BookingRoomRequestedHandler;
import com.booking.system.booking.service.domain.ports.spi.messaging.listener.BookingRoomRequestedListener;
import com.booking.system.commons.domain.core.event.BookingRoomRequestedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomRequestedRabbitMQListener implements BookingRoomRequestedListener {

    private final BookingRoomRequestedHandler handler;


    @RabbitListener(queues = "${app.rabbitmq.queue.booking-room-requested}")
    public void listen(final BookingRoomRequestedEvent event) {
        log.info(
                "BookingRoomRequestedEvent received {}",
                event
        );
            this.handler.handle(event);
    }
}
