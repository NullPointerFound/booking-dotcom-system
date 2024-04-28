package com.booking.system.booking.service.dataaccess.db.repository;

import com.booking.system.booking.service.dataaccess.db.entity.BookingRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRoomJpaRepository extends JpaRepository<BookingRoomEntity, UUID> {
}
