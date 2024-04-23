package com.booking.system.hotel.service.dataacess.db.repository.adapters;

import com.booking.system.hotel.service.dataacess.db.mapper.HotelDatabaseMapper;
import com.booking.system.hotel.service.dataacess.db.repository.HotelCategoryJpaRepository;
import com.booking.system.hotel.service.dataacess.db.repository.HotelJpaRepository;
import com.booking.system.hotel.service.dataacess.db.repository.RoomJpaRepository;
import com.booking.system.hotel.service.domain.domain.entity.Hotel;
import com.booking.system.hotel.service.domain.domain.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.ports.queries.SearchHotelAvailableQueryResult;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelRepositoryAdapter implements HotelRepository {


    private final HotelJpaRepository hotelJpaRepository;
    private final HotelCategoryJpaRepository hotelCategoryJpaRepository;
    private final RoomJpaRepository roomJpaRepository;
    private final HotelDatabaseMapper hotelDatabaseMapper;

    public HotelRepositoryAdapter(HotelJpaRepository hotelJpaRepository,
                                  HotelCategoryJpaRepository hotelCategoryJpaRepository,
                                  RoomJpaRepository roomJpaRepository,
                                  HotelDatabaseMapper hotelDatabaseMapper) {
        this.hotelJpaRepository = hotelJpaRepository;
        this.hotelCategoryJpaRepository = hotelCategoryJpaRepository;
        this.roomJpaRepository = roomJpaRepository;
        this.hotelDatabaseMapper = hotelDatabaseMapper;
    }

    @Override
    public void register(final Hotel newHotel) {
        final var newHotelEntity = this.hotelDatabaseMapper.hotelToHotelEntity(newHotel);
        this.hotelJpaRepository.save(newHotelEntity);
        final var newRooms = this.hotelDatabaseMapper.roomsToRoomEntitySet(newHotel.getRooms());
        this.roomJpaRepository.saveAll(newRooms);
    }

    @Override
    public boolean existsCategoryById(HotelCategoryId hotelCategoryId) {
        return this.hotelCategoryJpaRepository.existsById(hotelCategoryId.getValue());
    }

    @Override
    public List<SearchHotelAvailableQueryResult> searchHotelAvailableBy(
            final String name,
            final String category,
            final String city,
            final String state
    ) {
        return this.hotelJpaRepository.findAllAvailableHotelByParameters(name, category, city, state).stream()
                .map(this.hotelDatabaseMapper::hotelEntityToSearchHotelAvailableQueryResult)
                .toList();
    }


}
