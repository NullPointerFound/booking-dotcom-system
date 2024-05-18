package com.booking.system.customer.domain.ports.spi.repository;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.customer.domain.core.entity.Customer;

public interface CustomerRepository {

    boolean customerExistsBy(CustomerId customerId);

    Customer findById(CustomerId customerId);

}
