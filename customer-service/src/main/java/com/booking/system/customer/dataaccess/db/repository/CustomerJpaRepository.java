package com.booking.system.customer.dataaccess.db.repository;

import com.booking.system.customer.dataaccess.db.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}

