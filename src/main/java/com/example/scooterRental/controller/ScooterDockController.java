package com.example.scooterRental.controller;


import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.service.ScooterDockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("scooter-dock")
public class ScooterDockController {

    private ScooterDockService scooterDockService;

    public ScooterDockController(ScooterDockService scooterDockService) {
        this.scooterDockService = scooterDockService;
    }

    //GET /scooter-dock/{scooterDockId}/scooters - żądanie będzie pozwalać na pobranie listy hulajnóg, które aktualnie znajdują się w stacji dokującej o danym id.
    @GetMapping(value = "/{scooterDockId}/scooters", produces = "application/json")
    public ResponseEntity<Set<Scooter>> getScooters(
            @PathVariable Long scooterDockId
    ) {
        return null;
    }
}
