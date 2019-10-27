package com.example.scooterRental.service;

import com.example.scooterRental.api.BasicResponse;
import org.springframework.http.ResponseEntity;


public interface RentalService {

    ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId);
    ResponseEntity<BasicResponse> returnScooter(Long scooterId, Long dockId);
}
