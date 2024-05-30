package com.booking.system.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PaymentServiceMain {

    public static void main(final String[] args) {
        SpringApplication.run(PaymentServiceMain.class, args);
    }

}
