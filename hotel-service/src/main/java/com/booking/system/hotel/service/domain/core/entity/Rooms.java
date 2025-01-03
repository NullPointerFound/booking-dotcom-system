package com.booking.system.hotel.service.domain.core.entity;

import com.booking.system.commons.domain.core.AbstractDomainList;
import com.booking.system.commons.domain.core.valueobject.RoomId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rooms extends AbstractDomainList<Room> {

    private Rooms(final List<Room> data) {
        super(data);
    }

    public static Rooms empty() {
        return new Rooms(new ArrayList<>());
    }

    public static Rooms newInstance(final List<Room> rooms) {
        return new Rooms(rooms);
    }

    public static Rooms of(final Room... rooms) {
        return new Rooms(List.of(rooms));
    }

    public void validate() {
        this.data().forEach(Room::validate);
    }

    public boolean notContainsId(final RoomId roomId) {
        return this.data().stream()
                .noneMatch(room -> roomId.equals(room.getId()));
    }
    public List<String> asRawIds() {
        return this.data().stream()
                .map(Room::getId)
                .map(RoomId::toString)
                .collect(Collectors.toList());
    }
}
