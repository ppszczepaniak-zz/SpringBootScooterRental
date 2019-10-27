package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.exception.CommonConflictException;
import com.example.scooterRental.model.Scooter;
import com.example.scooterRental.model.ScooterDock;
import com.example.scooterRental.model.UserAccount;
import com.example.scooterRental.repository.ScooterDockRepository;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.repository.UserAccountRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RentalServiceImpl extends AbstractCommonService implements RentalService {

    private ScooterRepository scooterRepository;
    private UserAccountRepository userAccountRepository;
    private ScooterDockRepository scooterDockRepository;

    public RentalServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, UserAccountRepository userAccountRepository, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.userAccountRepository = userAccountRepository;
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId) {

        //find user account
        UserAccount userAccount = extractUserAccountFromRepository(accountId);

        //find scooter
        Scooter scooter = extractScooterFromRepository(scooterId);

        //validate
        checkScooterAvailableToRent(scooter);
        checkUserAccountAlreadyHasAnyRental(userAccount);
        checkUserAccountHaveEnoughMoney(userAccount, scooter.getRentalPrice());

        //...profit!!
        finalizeScooterRental(userAccount, scooter);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK004)); //Poprawnie wypożyczono hulajnogę.
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> returnScooter(Long scooterId, Long dockId) {

        //find dock
        ScooterDock scooterDock = extractDockFromRepository(dockId);

        //find scooter
        Scooter scooter = extractScooterFromRepository(scooterId);

        //validate
        checkScooterRented(scooter);
        checkIfThereIsAvailablePlaceInDock(scooterDock);

        //...profit!!
        finalizeScooterReturn(scooter, scooterDock);

        return ResponseEntity.ok(BasicResponse.of(msgSource.OK005)); //Poprawnie zwrócono hulajnogę.
    }


    //========private methods==========
    private UserAccount extractUserAccountFromRepository(Long accountId) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(accountId);
        if (!userAccount.isPresent()) {
            throw new CommonConflictException(msgSource.ERR006); //Konto użytkownika o danym id nie istnieje.
        }
        return userAccount.get();
    }

    private Scooter extractScooterFromRepository(Long scooterId) {
        Optional<Scooter> scooter = scooterRepository.findById(scooterId);
        if (!scooter.isPresent()) {
            throw new CommonConflictException(msgSource.ERR010); //Hulajnoga o podanym id nie istnieje.
        }
        return scooter.get();
    }

    private void checkScooterAvailableToRent(Scooter scooter) {
        if (scooter.getScooterDock() == null || scooter.getUserAccount() != null) {
            throw new CommonConflictException(msgSource.ERR011); //Hulajnoga o podanym id nie jest dostępna do wypożyczenia.
        }
    }

    private void checkUserAccountAlreadyHasAnyRental(UserAccount userAccount) {
        if (userAccount.getScooter() != null) {
            throw new CommonConflictException(msgSource.ERR012); //Konto o podanym id posiada już wypożyczoną hulajnogę.
        }
    }

    private void checkUserAccountHaveEnoughMoney(UserAccount userAccount, BigDecimal rentalPrice) {
        if (userAccount.getBalance().compareTo(rentalPrice) < 0) {
            throw new CommonConflictException(msgSource.ERR013); //Konto o podanym id nie posiada wystarczających środków na koncie.
        }
    }

    private void finalizeScooterRental(UserAccount userAccount, Scooter scooter) {
        userAccount.setBalance(userAccount.getBalance().subtract(scooter.getRentalPrice()));
        scooter.setScooterDock(null);
        scooter.setUserAccount(userAccount);
        scooterRepository.save(scooter);
    }


    private void finalizeScooterReturn(Scooter scooter, ScooterDock scooterDock) {
        scooter.setScooterDock(scooterDock);
        scooter.setUserAccount(null);
        scooterRepository.save(scooter);
    }

    private void checkIfThereIsAvailablePlaceInDock(ScooterDock scooterDock) {
        if (scooterDock.getScooters().size() >= scooterDock.getAvailablePlace()) { //if number of scooters at dock is >= numbers of total places in dock
            throw new CommonConflictException(msgSource.ERR009); //"Dok jest pełny."
        }
    }

    private void checkScooterRented(Scooter scooter) {
        if (scooter.getScooterDock() != null || scooter.getUserAccount() == null) {
            throw new CommonConflictException(msgSource.ERR014); //Hulajnoga o podanym id nie była wypożyczona.
        }

    }

    private ScooterDock extractDockFromRepository(Long dockId) {
        Optional<ScooterDock> scooterDock = scooterDockRepository.findById(dockId);
        if (!scooterDock.isPresent()) {
            throw new CommonConflictException(msgSource.ERR008); //Dok o podanym id nie istnieje.
        }
        return scooterDock.get();
    }

}
