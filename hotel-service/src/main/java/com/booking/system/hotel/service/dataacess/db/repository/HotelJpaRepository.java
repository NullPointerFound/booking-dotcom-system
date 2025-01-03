package com.booking.system.hotel.service.dataacess.db.repository;

import com.booking.system.hotel.service.dataacess.db.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HotelJpaRepository extends JpaRepository<HotelEntity, UUID> {

    @Query("""
    SELECT hotel FROM HotelEntity hotel
    LEFT JOIN hotel.category category
    LEFT JOIN hotel.locality locality
    WHERE
      ( lower(hotel.name) LIKE CONCAT('%', lower(:name), '%') or :name is null or :name = '' ) AND
      ( lower(category.name) LIKE CONCAT('%', lower(:category), '%') or :category is null or :category = '' ) AND
      ( lower(locality.city) LIKE CONCAT('%', lower(:city), '%') or :city is null or :city = '' ) AND
      ( lower(locality.state) LIKE CONCAT('%', lower(:state), '%') or :state is null or :state = '' )
    """)
    List<HotelEntity> findAllAvailableHotelByParameters(String name, String category, String city, String state);
}
