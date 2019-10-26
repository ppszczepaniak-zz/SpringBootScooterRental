package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.request.AddScooterRequest;
import com.example.scooterRental.api.response.AddScooterResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.model.ScooterDock;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScooterServiceImpl extends AbstractCommonService implements ScooterService {

    private ScooterRepository scooterRepository;
    private ScooterDockRepository scooterDockRepository;

    public ScooterServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request) {
        //1st validate if request is correct
        validateAddScooterRequests(request);
        //then find correct docking station
        ScooterDock scooterDock = extractScooterDockFromRepository(request.getScooterDockId());
        //this one is self expl.
        checkIfThereIsAvailablePlaceInDock(scooterDock);
        //then add scooter to DB
        Scooter addedScooter = addScooterToScooterRepo(request, scooterDock);

        //and gimme back my response
        return ResponseEntity.ok(new AddScooterResponse(msgSource.OK003, addedScooter.getId())); //OK003 = "Poprawnie dodano hulajnogę do systemu."
    }

    private Scooter addScooterToScooterRepo(AddScooterRequest request, ScooterDock scooterDock) {
        //TODO fill in missing data for Scooter and add it to ScooterRepo
        return null;
    }

    private void checkIfThereIsAvailablePlaceInDock(ScooterDock scooterDock) {
        //TODO throw sth
    }

    private ScooterDock extractScooterDockFromRepository(Long scooterDockId) {
        //TODO find dock in DB or throw sth
        return null;
    }

    private void validateAddScooterRequests(AddScooterRequest request) {
        //TODO throw sth
    }
}
