package com.booking.system.booking.service.dataaccess.db.repository;

import com.booking.system.booking.service.dataaccess.db.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {
}
