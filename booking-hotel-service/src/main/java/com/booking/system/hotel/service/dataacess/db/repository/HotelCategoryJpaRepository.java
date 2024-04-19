package com.booking.system.hotel.service.dataacess.db.repository;

import com.booking.system.hotel.service.dataacess.db.entity.HotelCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelCategoryJpaRepository extends JpaRepository<HotelCategoryEntity, UUID> {
}
