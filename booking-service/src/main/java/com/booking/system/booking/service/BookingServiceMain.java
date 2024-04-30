package com.booking.system.booking.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookingServiceMain {

    public static void main(final String[] args) {
        SpringApplication.run(BookingServiceMain.class, args);
    }

}
