package com.example.scooterRental.repository;

import com.example.scooterRental.model.ScooterDock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterDockRepository extends JpaRepository<ScooterDock, Long> {
}
