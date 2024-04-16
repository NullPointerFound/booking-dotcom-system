package com.booking.system.commons.message;

public class ApplicationMessage {

    public static final String HOTEL_CATEGORY_NOT_NULL = "hotel.category.must.not.be.null";
    public static final String HOTEL_LOCALITY_NOT_NULL = "hotel.locality.must.not.be.null";
    public static final String HOTEL_ROOM_NOT_NULL = "hotel.room.must.not.be.null";
    public static final String HOTEL_ROOMS_INVALID = "hotel.rooms.must.not.be.null-or-empty";
    public static final String HOTEL_NAME_INVALID = "hotel.name.must.not.be.null-or-empty";
    public static final String HOTEL_DESCRIPTION_INVALID = "hotel.description.must.not.be.null-or-empty";
    public static final String HOTEL_STREET_INVALID = "hotel.street.must.not.be.null-or-empty";
    public static final String HOTEL_ZIP_INVALID = "hotel.zip.invalid";
    public static final String HOTEL_ROOM_QUANTITY_NOT_NULL = "hotel.room.quantity.must.not.be.null";
    public static final String HOTEL_ROOM_QUANTITY_INVALID = "hotel.room.quantity.must.not.be.zero-or-negative";
    public static final String HOTEL_ROOM_RELATIONSHIP_NOT_FOUND = "hotel.room.relationship.not.found";
    public static final String HOTEL_ROOM_NAME_NOT_NULL = "hotel.room.name.must.not.be.null";
    public static final String HOTEL_ROOM_DESCRIPTION_NOT_NULL = "hotel.room.description.must.not.be.null";
    public static final String HOTEL_ROOM_CAPACITY_NOT_NULL = "hotel.room.capacity.must.not.be.null";
    public static final String HOTEL_ROOM_CAPACITY_INVALID = "hotel.room.capacity.must.not.be.zero-or-negative";
    public static final String HOTEL_ROOM_CURRENT_PRICE_NOT_NULL = "hotel.room.current-price.must.not.be.null";
    public static final String HOTEL_ROOM_CURRENT_PRICE_INVALID = "hotel.room.current-price.must.not.be.zero-or-negative";
    public static final String HOTEL_CATEGORY_NOT_FOUND = "hotel.category.not.found";
    public static final String HOTEL_LOCALITY_NOT_FOUND = "hotel.locality.not.found";


    private ApplicationMessage() {
    }
}
