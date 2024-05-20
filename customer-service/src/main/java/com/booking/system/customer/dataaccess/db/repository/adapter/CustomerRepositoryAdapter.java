package com.booking.system.customer.dataaccess.db.repository.adapter;

import com.booking.system.commons.domain.core.valueobject.CustomerId;
import com.booking.system.customer.dataaccess.db.entity.CustomerEntity;
import com.booking.system.customer.dataaccess.db.mapper.CustomerDatabaseMapper;
import com.booking.system.customer.dataaccess.db.repository.CustomerJpaRepository;
import com.booking.system.customer.domain.core.entity.Customer;
import com.booking.system.customer.domain.ports.spi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.booking.system.customer.domain.core.exception.CustomerNotFoundException;

@Component
@AllArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository repository;
    private final CustomerDatabaseMapper customerDatabaseMapper;

    @Override
    public boolean customerExistsBy(final CustomerId customerId) {
        return this.repository.existsById(customerId.getValue());
    }

    @Override
    public Customer findById(final CustomerId customerId) {
        return this.repository.findById(customerId.getValue())
                .map(this.customerDatabaseMapper::customerEntityToCustomer)
                .orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerEntity findCustomerEntityById(final CustomerId customerId) {
        return this.repository.findById(customerId.getValue())
                .orElseThrow(CustomerNotFoundException::new);
    }

}
