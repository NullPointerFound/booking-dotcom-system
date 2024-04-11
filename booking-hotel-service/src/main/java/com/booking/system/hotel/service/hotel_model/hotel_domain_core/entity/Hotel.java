package com.booking.system.hotel.service.hotel_model.hotel_domain_core.entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Hotel {

    private final String name;
    private final String description;


    public Hotel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
