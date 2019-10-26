package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.repository.UserAccountRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl extends AbstractCommonService implements RentalService {


    private ScooterRepository scooterRepository;
    private UserAccountRepository userAccountRepository;

    public RentalServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, UserAccountRepository userAccountRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId) {
        return null;
    }
}
