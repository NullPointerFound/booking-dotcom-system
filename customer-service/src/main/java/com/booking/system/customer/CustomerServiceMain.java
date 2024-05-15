package com.booking.system.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CustomerServiceMain {

    public static void main(final String[] args) {
        SpringApplication.run(CustomerServiceMain.class, args);
    }

}
