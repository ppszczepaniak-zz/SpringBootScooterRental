package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.api.request.CreateUserAccountRequest;
import com.example.scooterRental.api.response.CreateUserAccountResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.model.UserAccount;
import com.example.scooterRental.repository.UserAccountRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class UserAccountServiceImpl extends AbstractCommonService implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request) {
        validateCreateAccountRequest(request);
        checkOwnerEmailAlreadyExists(request.getOwnerEmail());
        UserAccount addedAccount = addUserAccountToUserRepository(request); //gives back ID from DB, which will go to Reponse

        return ResponseEntity.ok(new CreateUserAccountResponse(msgSource.OK001, addedAccount.getId()));  //"Poprawnie utworzono konto użytkownika."
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount) {
        //TODO
        return null;
    }

    //private methods
    private void validateCreateAccountRequest(CreateUserAccountRequest request) {
        //TODO throws sth
    }

    private void checkOwnerEmailAlreadyExists(String ownerEmail) {
        //TODO throws sth
    }

    private UserAccount addUserAccountToUserRepository(CreateUserAccountRequest request) {
        //TODO fill in missing data and add to user Repo
        return null;
    }

}
