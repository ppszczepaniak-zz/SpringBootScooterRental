package com.example.scooterRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
//we add this so Spring will use our messages
@ComponentScan(basePackages = {
        "com.example.scooterRental.controller",
        "com.example.scooterRental.model",
        "com.example.scooterRentalApp.repository",
        "com.example.scooterRentalApp.common",
        "com.example.scooterRentalApp.service"
})
//INFO: this is probably  NOT necessary if all packages are "below" main class, as in this app
//in order to properly load components which represent controller, model, repositotory,... we must add this annotation (ComponentScan),
// which describes which packages should be scanned in order to find Spring components

public class ScooterRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterRentalApplication.class, args);
    }
}
