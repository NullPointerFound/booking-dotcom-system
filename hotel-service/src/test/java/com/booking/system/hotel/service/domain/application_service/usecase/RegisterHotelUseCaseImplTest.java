package com.booking.system.hotel.service.domain.application_service.usecase;


import java.math.BigDecimal;
import java.util.List;

import com.booking.system.commons.domain.message.ApplicationMessage;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelRoomInput;
import com.booking.system.hotel.service.domain.application_service.mapper.HotelUseCaseMapperImpl;
import com.booking.system.hotel.service.domain.core.entity.Hotel;
import com.booking.system.hotel.service.domain.core.exception.HotelDomainException;
import com.booking.system.hotel.service.domain.core.valueobject.HotelCategoryId;
import com.booking.system.hotel.service.domain.core.valueobject.LocalityId;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.repository.LocalityRepository;
import com.booking.system.hotel.service.domain.ports.usecase.RegisterHotelUseCase;
import org.assertj.core.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

@Tags({@Tag("unit")})
@DisplayName("Register hotel use case tests")
@ExtendWith(MockitoExtension.class)
class RegisterHotelUseCaseImplTest {

    private static final String CATEGORY_ID = "e1bf8402-9441-4774-8061-9444fc8bdb28";
    private static final String LOCALITY_ID = "9898b2f1-9861-42c5-a84f-42bba41fbe00";
    private RegisterHotelUseCase rhu;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private LocalityRepository localityRepository;

    @BeforeEach
    void setUp() {
        this.rhu = new RegisterHotelUseCaseImpl(
                this.hotelRepository,
                this.localityRepository,
                new HotelUseCaseMapperImpl()
        );
    }

    @Test
    @DisplayName("Should create a new Hotel with rooms")
    void test1() {

        Mockito.doReturn(true)
                .when(this.hotelRepository)
                .existsCategoryById(Mockito.any(HotelCategoryId.class));
        Mockito.doReturn(true)
                .when(this.localityRepository)
                .existsLocalityById(Mockito.any(LocalityId.class));
        Mockito.doNothing()
                .when(this.hotelRepository)
                .register(Mockito.any(Hotel.class));

        final RegisterHotelOutput output = this.rhu.execute(
                RegisterHotelInput.builder()
                        .name("Courtyard Philadelphia City Avenue")
                        .description(
                                "Set near stores and casual eateries in Wynnefield Heights, this modern hotel off I-76 is 4 miles from the Please Touch Museum and 7 miles from the Pennsylvania Convention Center.\n" +
                                        "Modern rooms come with free Wi-Fi and flat-screen TVs, in addition to coffeemakers and pillow-top mattresses; some feature pull-out sofas. Suites add separate living rooms and kitchenettes. Room service is available.\n" +
                                        "Parking and electric car charging are complimentary. There's also a simple bistro/bar, a convenience store and a 24-hour business center, as well as a gym and an indoor pool."
                        )
                        .categoryId(CATEGORY_ID)
                        .localityId(LOCALITY_ID)
                        .street("A4100 Presidential Blvd, Philadelphia")
                        .zip("19131-000")
                        .rooms(List.of(
                                RegisterHotelRoomInput.builder()
                                        .name("Room Twin")
                                        .capacity(1)
                                        .description("This double room has a tile/marble floor and air conditioning.")
                                        .currentPrice(BigDecimal.valueOf(350))
                                        .quantity(2)
                                        .build(),
                                RegisterHotelRoomInput.builder()
                                        .name("Room Duplex")
                                        .capacity(3)
                                        .description("This triple room has air conditioning, dining area and tile/marble floor.")
                                        .currentPrice(BigDecimal.valueOf(450))
                                        .quantity(2)
                                        .build(),
                                RegisterHotelRoomInput.builder()
                                        .name("Familly Room")
                                        .capacity(4)
                                        .description("Best choice for a family.")
                                        .currentPrice(BigDecimal.valueOf(550))
                                        .quantity(2)
                                        .build()
                        ))
                        .build()
        );

        Mockito.verify(this.hotelRepository, Mockito.times(1))
                .existsCategoryById(Mockito.any(HotelCategoryId.class));
        Mockito.verify(this.localityRepository, Mockito.times(1))
                .existsLocalityById(Mockito.any(LocalityId.class));
        Mockito.verify(this.hotelRepository, Mockito.times(1))
                .register(Mockito.any(Hotel.class));

        Assertions.assertThat(output.hotelId()).isNotNull();
        Assertions.assertThat(output.roomsId()).hasSize(3);
    }

    @Test
    @DisplayName("Should throw exception when category not exists")
    void test2() {

        Mockito.doReturn(false)
                .when(this.hotelRepository)
                .existsCategoryById(Mockito.any(HotelCategoryId.class));

        final var input = RegisterHotelInput.builder()
                .name("Courtyard Philadelphia City Avenue")
                .description(
                        "Set near stores and casual eateries in Wynnefield Heights, this modern hotel off I-76 is 4 miles from the Please Touch Museum and 7 miles from the Pennsylvania Convention Center.\n" +
                                "Modern rooms come with free Wi-Fi and flat-screen TVs, in addition to coffeemakers and pillow-top mattresses; some feature pull-out sofas. Suites add separate living rooms and kitchenettes. Room service is available.\n" +
                                "Parking and electric car charging are complimentary. There's also a simple bistro/bar, a convenience store and a 24-hour business center, as well as a gym and an indoor pool."
                )
                .categoryId(CATEGORY_ID)
                .localityId(LOCALITY_ID)
                .street("A4100 Presidential Blvd, Philadelphia")
                .zip("19131-000")
                .rooms(List.of(
                        RegisterHotelRoomInput.builder()
                                .name("Room Twin")
                                .capacity(1)
                                .description("This double room has a tile/marble floor and air conditioning.")
                                .currentPrice(BigDecimal.valueOf(350))
                                .quantity(2)
                                .build(),
                        RegisterHotelRoomInput.builder()
                                .name("Room Duplex")
                                .capacity(3)
                                .description("This triple room has air conditioning, dining area and tile/marble floor.")
                                .currentPrice(BigDecimal.valueOf(450))
                                .quantity(2)
                                .build(),
                        RegisterHotelRoomInput.builder()
                                .name("Familly Room")
                                .capacity(4)
                                .description("Best choice for a family.")
                                .currentPrice(BigDecimal.valueOf(550))
                                .quantity(2)
                                .build()
                ))
                .build();


        Assertions.assertThatThrownBy(() -> this.rhu.execute(input))
                .isInstanceOf(HotelDomainException.class)
                .hasMessage(ApplicationMessage.HOTEL_CATEGORY_NOT_FOUND);

        Mockito.verify(this.hotelRepository, Mockito.times(1))
                .existsCategoryById(Mockito.any(HotelCategoryId.class));
        Mockito.verify(this.localityRepository, Mockito.never())
                .existsLocalityById(Mockito.any(LocalityId.class));
        Mockito.verify(this.hotelRepository, Mockito.never())
                .register(Mockito.any(Hotel.class));
    }

    @Test
    @DisplayName("Should throw exception when locality not exists")
    void test3() {

        Mockito.doReturn(true)
                .when(this.hotelRepository)
                .existsCategoryById(Mockito.any(HotelCategoryId.class));
        Mockito.doReturn(false)
                .when(this.localityRepository)
                .existsLocalityById(Mockito.any(LocalityId.class));

        final var input = RegisterHotelInput.builder()
                .name("Courtyard Philadelphia City Avenue")
                .description(
                        "Set near stores and casual eateries in Wynnefield Heights, this modern hotel off I-76 is 4 miles from the Please Touch Museum and 7 miles from the Pennsylvania Convention Center.\n" +
                                "Modern rooms come with free Wi-Fi and flat-screen TVs, in addition to coffeemakers and pillow-top mattresses; some feature pull-out sofas. Suites add separate living rooms and kitchenettes. Room service is available.\n" +
                                "Parking and electric car charging are complimentary. There's also a simple bistro/bar, a convenience store and a 24-hour business center, as well as a gym and an indoor pool."
                )
                .categoryId(CATEGORY_ID)
                .localityId(LOCALITY_ID)
                .street("A4100 Presidential Blvd, Philadelphia")
                .zip("19131-000")
                .rooms(List.of(
                        RegisterHotelRoomInput.builder()
                                .name("Room Twin")
                                .capacity(1)
                                .description("This double room has a tile/marble floor and air conditioning.")
                                .currentPrice(BigDecimal.valueOf(350))
                                .quantity(2)
                                .build(),
                        RegisterHotelRoomInput.builder()
                                .name("Room Duplex")
                                .capacity(3)
                                .description("This triple room has air conditioning, dining area and tile/marble floor.")
                                .currentPrice(BigDecimal.valueOf(450))
                                .quantity(2)
                                .build(),
                        RegisterHotelRoomInput.builder()
                                .name("Familly Room")
                                .capacity(4)
                                .description("Best choice for a family.")
                                .currentPrice(BigDecimal.valueOf(550))
                                .quantity(2)
                                .build()
                ))
                .build();
        Assertions.assertThatThrownBy(() -> this.rhu.execute(input))
                .isInstanceOf(HotelDomainException.class)
                .hasMessage(ApplicationMessage.HOTEL_LOCALITY_NOT_FOUND);

        Mockito.verify(this.hotelRepository, Mockito.times(1))
                .existsCategoryById(Mockito.any(HotelCategoryId.class));
        Mockito.verify(this.localityRepository, Mockito.times(1))
                .existsLocalityById(Mockito.any(LocalityId.class));
        Mockito.verify(this.hotelRepository, Mockito.never())
                .register(Mockito.any(Hotel.class));
    }

}