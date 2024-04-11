package com.booking.system.hotel.service.hotel_model.hotel_domain_core.valueobject;

import com.booking.system.hotel.service.hotel_model.hotel_domain_core.exception.HotelDomainException;
import lombok.Builder;

@Builder
public class HotelAddress {

    private static final String ZIP_PATTERN = "^\\d{5}-\\d{3}$";
    private final String zip;
    private final String street;

    public HotelAddress(final String zip, final String street) {
        this.zip = zip;
        this.street = street;
    }

    public String getZip() {
        return this.zip;
    }

    public String getStreet() {
        return this.street;
    }
}
