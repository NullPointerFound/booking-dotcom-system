package com.booking.system.booking.service.domain.ports.spi.repository;

import com.booking.system.booking.service.domain.core.entity.Room;
import com.booking.system.commons.domain.core.valueobject.RoomId;

import java.util.Collection;
import java.util.List;

public interface RoomRepository {

    List<Room> findAllByRoomId(Collection<? extends RoomId> ids);
}
