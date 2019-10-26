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
        "com.example.scooterRental.repository",
        "com.example.scooterRental.common",
        "com.example.scooterRental.service"
})
//INFO: this is probably  NOT necessary if all packages are "below" main class, as in this app
//in order to properly load components which represent controller, model, repositotory,... we add this annotation (ComponentScan),
// which describes which packages should be scanned in order to find Spring components (others will be omitted)

public class ScooterRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterRentalApplication.class, args);
    }
}
