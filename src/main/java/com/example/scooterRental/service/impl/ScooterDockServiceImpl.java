package com.example.scooterRental.service.impl;

import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.ScooterDockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ScooterDockServiceImpl extends AbstractCommonService implements ScooterDockService {

    private ScooterDockRepository scooterDockRepository;

    public ScooterDockServiceImpl(MsgSource msgSource, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    public ResponseEntity<Set<Scooter>> getAllDockedScooters(Long scooterDockId) {
        return null;
    }
}
