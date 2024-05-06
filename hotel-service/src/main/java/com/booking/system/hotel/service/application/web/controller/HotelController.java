package com.booking.system.hotel.service.application.web.controller;

import com.booking.system.commons.application.dto.CollectionResponse;
import com.booking.system.commons.application.dto.Response;
import com.booking.system.commons.application.dto.impl.ResponseEntityAdapter;
import com.booking.system.hotel.service.application.service.HotelApplicationService;
import com.booking.system.hotel.service.domain.application_service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelApplicationService hotelApplicationService;

    public HotelController(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @PostMapping
    public ResponseEntity<Response<RegisterHotelOutput>> register(@RequestBody final RegisterHotelInput input) {
        final var output = this.hotelApplicationService.register(input);
        return ResponseEntityAdapter.of(output);
    }

    @GetMapping
    public ResponseEntity<CollectionResponse<SearchHotelAvailableOutput>> searchHotelAvailableBy(
            @RequestParam(value = "name", required = false, defaultValue = "") final String name,
            @RequestParam(value = "city", required = false, defaultValue = "") final String city,
            @RequestParam(value = "state", required = false, defaultValue = "") final String state,
            @RequestParam(value = "category", required = false, defaultValue = "") final String category
    ){
        final List<SearchHotelAvailableOutput> output = this.hotelApplicationService.searchHotelAvailableBy(
                SearchHotelAvailableInput.builder()
                        .name(name)
                        .category(category)
                        .city(city)
                        .state(state)
                        .build()
        );

        return ResponseEntityAdapter.items(output);
    }

    @PostMapping("/booking")
    public ResponseEntity<Response<BookingRoomOutput>> bookingRoom(@RequestBody final BookingRoomInput input) {
        final var output = this.hotelApplicationService.bookingRoomRequest(input);
        return ResponseEntityAdapter.of(output);
    }
}