package com.example.scooterRental.service;

import com.example.scooterRental.api.request.AddScooterRequest;
import com.example.scooterRental.api.response.AddScooterResponse;
import org.springframework.http.ResponseEntity;

public interface ScooterService {

    ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request);
}
