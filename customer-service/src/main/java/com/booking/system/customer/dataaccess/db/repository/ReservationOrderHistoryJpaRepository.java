package com.booking.system.customer.dataaccess.db.repository;

import com.booking.system.customer.dataaccess.db.entity.ReservationOrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationOrderHistoryJpaRepository extends JpaRepository<ReservationOrderHistoryEntity, UUID> {
}
