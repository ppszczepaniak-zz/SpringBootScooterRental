package com.example.scooterRental.service;

import com.example.scooterRental.api.BasicResponse;
import org.springframework.http.ResponseEntity;


public interface RentalService {

    ResponseEntity<BasicResponse> createUserAccount(Long scooterId, Long accountId);
}
