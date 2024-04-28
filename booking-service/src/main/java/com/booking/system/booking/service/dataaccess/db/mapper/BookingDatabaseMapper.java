package com.booking.system.booking.service.dataaccess.db.mapper;

import com.booking.system.booking.service.dataaccess.db.entity.BookingEntity;
import com.booking.system.booking.service.dataaccess.db.entity.RoomEntity;
import com.booking.system.booking.service.domain.core.entity.Booking;
import com.booking.system.booking.service.domain.core.entity.Room;

public interface BookingDatabaseMapper {

    Booking bookingEntityToBooking(BookingEntity bookingEntity);

    BookingEntity bookingToBookingEntity(Booking booking);

    Room roomEntityToRoom(RoomEntity roomEntity);
}
