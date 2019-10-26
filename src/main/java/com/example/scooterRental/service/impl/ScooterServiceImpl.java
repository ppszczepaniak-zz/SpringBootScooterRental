package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.request.AddScooterRequest;
import com.example.scooterRental.api.response.AddScooterResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.ScooterService;
import org.springframework.http.ResponseEntity;

public class ScooterServiceImpl extends AbstractCommonService implements ScooterService {

    private ScooterRepository scooterRepository;
    private ScooterDockRepository scooterDockRepository;

    public ScooterServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    public ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request) {
        return null;
    }
}
