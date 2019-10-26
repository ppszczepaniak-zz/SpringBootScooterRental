package com.example.scooterRental.repository;

import com.example.scooterRental.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {
}
