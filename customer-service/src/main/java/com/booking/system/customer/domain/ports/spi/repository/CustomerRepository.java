package com.booking.system.customer.domain.ports.spi.repository;

import com.booking.system.commons.domain.core.valueobject.CustomerId;

public interface CustomerRepository {

    boolean customerExistsBy(CustomerId customerId);

}
