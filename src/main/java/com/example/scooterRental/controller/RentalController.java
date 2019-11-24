package com.example.scooterRental.controller;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    //PUT /rental/{scooterId}/scooter - żądanie będzie pozwalało na wypożyczenie hulajnogi o wskazanym id.

    @PutMapping(value = "/{scooterId}/scooter", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BasicResponse> rentScooter(
            @PathVariable Long scooterId,
            @RequestParam Long accountId
    ) {
        return rentalService.rentScooter(scooterId, accountId);
    }


    @PutMapping(value = "/{scooterId}/return", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BasicResponse> returnScooter(
            @PathVariable Long scooterId,
            @RequestParam Long dockId
    ) {
        return rentalService.returnScooter(scooterId, dockId);
    }

}
