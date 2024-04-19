package com.booking.system.hotel.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookingHotelServiceMain {

    public static void main(final String[] args) {
        SpringApplication.run(BookingHotelServiceMain.class, args);
    }

}
