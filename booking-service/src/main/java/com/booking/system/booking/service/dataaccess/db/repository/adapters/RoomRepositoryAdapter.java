package com.booking.system.booking.service.dataaccess.db.repository.adapters;

import com.booking.system.booking.service.dataaccess.db.entity.RoomEntity;
import com.booking.system.booking.service.dataaccess.db.mapper.BookingDatabaseMapper;
import com.booking.system.booking.service.dataaccess.db.repository.RoomJpaRepository;
import com.booking.system.booking.service.domain.core.entity.Room;
import com.booking.system.booking.service.domain.core.exception.RoomNotFoundException;
import com.booking.system.booking.service.domain.ports.spi.repository.RoomRepository;
import com.booking.system.commons.domain.core.valueobject.RoomId;
import com.booking.system.commons.domain.message.ApplicationMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomRepositoryAdapter implements RoomRepository {

    private final RoomJpaRepository roomJpaRepository;
    private final BookingDatabaseMapper bookingDatabaseMapper;

    public RoomRepositoryAdapter(final RoomJpaRepository roomJpaRepository, @Lazy final BookingDatabaseMapper bookingDatabaseMapper) {
        this.roomJpaRepository = roomJpaRepository;
        this.bookingDatabaseMapper = bookingDatabaseMapper;
    }

    public RoomEntity findRoomEntityById(final RoomId roomId) {
        return this.roomJpaRepository.findById(roomId.getValue())
                .orElseThrow(() -> new RoomNotFoundException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));
    }

    @Override
    public List<Room> findAllByRoomId(final Collection<? extends RoomId> ids) {
        return this.roomJpaRepository.findAllById(ids.stream()
                        .map(RoomId::getValue)
                        .collect(Collectors.toSet())
                ).stream()
                .map(this.bookingDatabaseMapper::roomEntityToRoom)
                .collect(Collectors.toList());
    }
}