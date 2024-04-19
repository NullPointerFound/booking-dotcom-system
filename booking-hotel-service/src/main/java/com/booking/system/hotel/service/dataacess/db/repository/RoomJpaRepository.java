package com.booking.system.hotel.service.dataacess.db.repository;

import com.booking.system.hotel.service.dataacess.db.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {
}
