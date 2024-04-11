package com.booking.system.hotel.service.hotel_model.hotel_domain_core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject.HotelCategoryId;

public class HotelCategory extends AbstractDomainEntity<HotelCategoryId> {

    private final String name;
    private final String description;

    protected HotelCategory(final HotelCategoryId id, final String name, final String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

}