package com.example.scooterRental.service.impl;

import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.exception.CommonConflictException;
import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.model.ScooterDock;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.ScooterDockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ScooterDockServiceImpl extends AbstractCommonService implements ScooterDockService {

    private ScooterDockRepository scooterDockRepository;

    public ScooterDockServiceImpl(MsgSource msgSource, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Set<Scooter>> getAllDockedScooters(Long scooterDockId) {
        Optional<ScooterDock> optionalScooterDock = scooterDockRepository.findById(scooterDockId);
        if(!optionalScooterDock.isPresent()) { //if doesn't exists in DB
            throw new CommonConflictException(msgSource.ERR008);  //ERR008 = "Dok o podanym id nie istnieje."
        }
        return ResponseEntity.ok(optionalScooterDock.get().getScooters());
    }
}
