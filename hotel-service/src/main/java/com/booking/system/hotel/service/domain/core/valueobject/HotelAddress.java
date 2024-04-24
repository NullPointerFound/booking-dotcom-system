package com.booking.system.hotel.service.domain.core.valueobject;

import com.booking.system.hotel.service.domain.core.exception.HotelDomainException;
import io.micrometer.common.util.StringUtils;
import lombok.Builder;
import com.booking.system.commons.message.ApplicationMessage;


@Builder
public class HotelAddress {

    private static final String ZIP_PATTERN = "^\\d{5}-\\d{3}$";
    private final String zip;
    private final String street;

    public HotelAddress(final String zip, final String street) {
        this.zip = zip;
        this.street = street;
    }

    public void validate() {
        if (StringUtils.isBlank(this.street)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_STREET_INVALID);
        }
        if (StringUtils.isBlank(this.zip) || !this.zip.matches(ZIP_PATTERN)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ZIP_INVALID);
        }
    }

    public String getZip() {
        return this.zip;
    }

    public String getStreet() {
        return this.street;
    }
}
