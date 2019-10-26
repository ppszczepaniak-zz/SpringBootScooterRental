package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.request.AddScooterRequest;
import com.example.scooterRental.api.response.AddScooterResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.exception.CommonBadRequestException;
import com.example.scooterRental.exception.CommonConflictException;
import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.model.ScooterDock;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.scooterRental.common.ValidationUtils.*;

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


    private void validateAddScooterRequests(AddScooterRequest request) {
        //checks if object is properly defined by user in request
        if (isNullOrEmpty(request.getModelName())
                || isNull(request.getRentalPrice())
                || isNull(request.getMaxSpeed())
                || isNull(request.getScooterDockId())
        ) {
            throw new CommonBadRequestException(msgSource.ERR001);  //"Dane wymagane do realizacji żądania są niekompletne."
        }

        if (isIncorrectMaxSpeed(request.getMaxSpeed())) {
            throw new CommonConflictException(msgSource.ERR007); //"Maksymalna prędkość hulajnogi powinna mieścić się w zakresie od 1 do 40."
        }
    }

    private ScooterDock extractScooterDockFromRepository(Long scooterDockId) {
        Optional<ScooterDock> optionalScooterDock = scooterDockRepository.findById(scooterDockId);
        if (!optionalScooterDock.isPresent()) {
            throw new CommonConflictException(msgSource.ERR008); //"Dok o podanym id nie istnieje."
        }
        return optionalScooterDock.get();
    }

    private void checkIfThereIsAvailablePlaceInDock(ScooterDock scooterDock) {
        if (scooterDock.getAvailablePlace() <= scooterDock.getScooters().size()) { //if number of all places at dock is <= numbers of scooters at the dock
            throw new CommonConflictException(msgSource.ERR009); //"Dok jest pełny."
        }
    }

    private Scooter addScooterToScooterRepo(AddScooterRequest request, ScooterDock scooterDock) {
        Scooter scooter = new Scooter();
        scooter.setModelName(request.getModelName());
        scooter.setMaxSpeed(request.getMaxSpeed());
        scooter.setRentalPrice(new BigDecimal(request.getRentalPrice()));
        scooter.setScooterDock(scooterDock);
        //don't set ID (DB does that) and UserAccount (it's for renting, not adding a scooter)

        return scooterRepository.save(scooter); //adds to DB + returns object
    }


}
