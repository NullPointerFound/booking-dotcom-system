package com.booking.system.hotel.service.domain.domain.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.hotel.service.domain.domain.valueobject.LocalityId;

public class Locality extends AbstractDomainEntity<LocalityId> {

    private final String country;
    private final String state;
    private final String city;


    public Locality(final LocalityId id, final String city, final String state, final String country) {
        super(id);
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
