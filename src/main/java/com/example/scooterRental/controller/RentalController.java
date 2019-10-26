package com.example.scooterRental.controller;

import com.example.scooterRental.api.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {

//PUT /rental/{scooterId}/scooter - żądanie będzie pozwalało na wypożyczenie hulajnogi o wskazanym id.


    @PutMapping(value = "/{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<BasicResponse> rentScooter(
            @PathVariable Long scooterId,
            @RequestParam Long accountId
    ) {
        return null;
    }

}
