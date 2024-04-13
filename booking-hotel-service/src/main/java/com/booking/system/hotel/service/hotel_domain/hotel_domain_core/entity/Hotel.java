package com.booking.system.hotel.service.hotel_domain.hotel_domain_core.entity;

import com.booking.system.commons.domain.core.AbstractDomainEntity;
import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.commons.message.ApplicationMessage;
import com.booking.system.hotel.service.hotel_domain.hotel_domain_core.exception.HotelDomainException;
import com.booking.system.hotel.service.hotel_domain.hotel_domain_core.valueobject.HotelAddress;
import com.booking.system.hotel.service.hotel_domain.hotel_domain_core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.hotel_domain.hotel_domain_core.valueobject.LocalityId;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

@SuperBuilder
public class Hotel extends AbstractDomainEntity<HotelId> {

    private final String name;
    private final String description;
    private final HotelAddress address;
    private final HotelCategoryId categoryId;
    private final LocalityId localityId;
    private final Rooms rooms;

    public Hotel(
            final HotelId id,
            final String name,
            final String description,
            final HotelCategoryId categoryId,
            final HotelAddress address,
            final LocalityId localityId,
            final Rooms rooms
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.address = address;
        this.localityId = localityId;
        this.rooms = rooms;
    }

    public void validate() {
        this.validateNameAndDescription();
        this.validateCategory();
        this.validateLocality();
        this.validateAddress();
        this.validateRooms();
    }

    private void validateRooms() {
        if (CollectionUtils.isEmpty(this.rooms)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_ROOMS_INVALID);
        }
        this.rooms.validate();
    }

    public void initialize() {
        this.setId(HotelId.newInstance());
        this.rooms.forEach(room -> room.initialize(this.getId()));
    }

    private void validateNameAndDescription() {
        if (StringUtils.isBlank(this.name)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_NAME_INVALID);
        }
        if (StringUtils.isBlank(this.description)) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_DESCRIPTION_INVALID);
        }
    }

    private void validateAddress() {
        this.address.validate();
    }

    private void validateLocality() {
        if (this.localityId == null || this.localityId.empty()) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_LOCALITY_NOT_NULL);
        }
    }

    private void validateCategory() {
        if (this.categoryId == null || this.categoryId.empty()) {
            throw new HotelDomainException(ApplicationMessage.HOTEL_CATEGORY_NOT_NULL);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HotelAddress getAddress() {
        return address;
    }

    public HotelCategoryId getCategoryId() {
        return categoryId;
    }

    public LocalityId getLocalityId() {
        return localityId;
    }

    public Rooms getRooms() {
        return rooms;
    }
}
