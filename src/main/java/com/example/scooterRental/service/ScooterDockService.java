package com.example.scooterRental.service;

import com.example.scooterRental.model.Scooter;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ScooterDockService {

    ResponseEntity<Set<Scooter>> getAllDockedScooters(Long scooterDockId);
}
