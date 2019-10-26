package com.example.scooterRental.controller;


import com.example.scooterRental.api.request.AddScooterRequest;
import com.example.scooterRental.api.response.AddScooterResponse;
import com.example.scooterRental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scooter")
public class ScooterController {

    private ScooterService scooterService;

    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }
    //POST /scooter/add - żądanie będzie pozwalało na wprowadzenie nowej hulajnogi do systemu.

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<AddScooterResponse> addScooter(
            @RequestBody AddScooterRequest request
            //to taki rodzaj DTO
    ) {
        return null;
    }
}
