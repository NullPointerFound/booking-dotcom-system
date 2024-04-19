package com.booking.system.hotel.service.dataacess.db.repository;

import com.booking.system.hotel.service.dataacess.db.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelJpaRepository extends JpaRepository<HotelEntity, UUID> {
}
