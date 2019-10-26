package com.example.scooterRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.example.scooterRental.controller",
		"com.example.scooterRental.model"
})
//in order to properly load components which represent controller we must add this annotation (ComponentScan),
// which describes which packages should be scanned in order to find Spring components

public class ScooterRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScooterRentalApplication.class, args);
	}
}
