package com.booking.system.hotel.service.application.web.controller;

import com.booking.system.commons.application.dto.Response;
import com.booking.system.commons.application.dto.impl.ResponseEntityAdapter;
import com.booking.system.hotel.service.application.service.HotelApplicationService;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelInput;
import com.booking.system.hotel.service.domain.application_service.dto.RegisterHotelOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}