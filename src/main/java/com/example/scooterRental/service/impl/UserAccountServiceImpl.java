package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.api.request.CreateUserAccountRequest;
import com.example.scooterRental.api.response.CreateUserAccountResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.repository.ScooterRepository;
import com.example.scooterRental.repository.UserAccountRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.UserAccountService;
import org.springframework.http.ResponseEntity;

public class UserAccountServiceImpl extends AbstractCommonService implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount) {
        return null;
    }

}
