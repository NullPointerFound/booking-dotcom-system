package com.booking.system.hotel.service.domain.application_service.usecase;

import com.booking.system.commons.domain.core.valueobject.HotelId;
import com.booking.system.hotel.service.domain.application_service.dto.SearchHotelAvailableInput;
import com.booking.system.hotel.service.domain.application_service.mapper.HotelUseCaseMapperImpl;
import com.booking.system.hotel.service.domain.ports.queries.SearchHotelAvailableQueryResult;
import com.booking.system.hotel.service.domain.ports.queries.SearchHotelAvailableRoomQueryResult;
import com.booking.system.hotel.service.domain.ports.repository.HotelRepository;
import com.booking.system.hotel.service.domain.ports.usecase.SearchHotelAvailableUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@Tags({@Tag("unit")})
@DisplayName("Search available Hotel use case tests")
@ExtendWith(MockitoExtension.class)
class SearchHotelAvailableUseCaseTest {

    private SearchHotelAvailableUseCase sut;
    @Mock
    private HotelRepository hotelRepository;

    @BeforeEach
    void setUp() {
        this.sut = new SearchHotelAvailableUseCaseImpl(
                this.hotelRepository,
                new HotelUseCaseMapperImpl()
        );
    }

    @Test
    @DisplayName("Should search available Hotel")
    void test1() {

        Mockito.doReturn(List.of(new FakeSearchHotelAvailableQueryResult()))
                .when(this.hotelRepository)
                .searchHotelAvailableBy("Hilton", null, null, null);

        final var output = this.sut.execute(
                SearchHotelAvailableInput.builder()
                        .name("Hilton")
                        .build()
        );

        org.assertj.core.api.Assertions.assertThat(output).hasSize(1);
        Assertions.assertThat(output).first()
                .usingRecursiveAssertion()
                .hasNoNullFields();
    }

    private static class FakeSearchHotelAvailableQueryResult implements SearchHotelAvailableQueryResult {
        @Override
        public HotelId getHotelId() {
            return HotelId.of("23ab46cd-cea3-4088-90c0-f15ba85164ff");
        }

        @Override
        public String getHotelName() {
            return "Hilton";
        }

        @Override
        public String getHotelDescription() {
            return "Tableside presentations are offered at this upscale Italian spot with free antipasti, pastas & more.";
        }

        @Override
        public String getHotelZip() {
            return "10004-000";
        }

        @Override
        public String getHotelStreet() {
            return "8 Stone St,";
        }

        @Override
        public String getHotelCategoryName() {
            return "Hotel";
        }

        @Override
        public String getHotelCity() {
            return "NYC";
        }

        @Override
        public String getHotelState() {
            return "New York";
        }

        @Override
        public String getHotelCountry() {
            return "United States";
        }

        @Override
        public List<SearchHotelAvailableRoomQueryResult> getRooms() {
            return new ArrayList<>();
        }
    }
}
