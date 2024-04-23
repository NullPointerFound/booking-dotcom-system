package com.booking.system.hotel.service.dataacess.db.repository;

import com.booking.system.hotel.service.dataacess.db.entity.LocalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalityJpaRepository extends JpaRepository<LocalityEntity, UUID> {
}
