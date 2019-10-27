package com.example.scooterRental.service.impl;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.api.request.CreateUserAccountRequest;
import com.example.scooterRental.api.response.CreateUserAccountResponse;
import com.example.scooterRental.common.MsgSource;
import com.example.scooterRental.exception.CommonBadRequestException;
import com.example.scooterRental.exception.CommonConflictException;
import com.example.scooterRental.model.UserAccount;
import com.example.scooterRental.repository.UserAccountRepository;
import com.example.scooterRental.service.AbstractCommonService;
import com.example.scooterRental.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.scooterRental.common.ValidationUtils.*;

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
        //validation 1st
        validateCreateAccountRequest(request);
        checkOwnerEmailAlreadyExists(request.getOwnerEmail());
        UserAccount addedAccount = addUserAccountToUserRepository(request); //gives back ID from DB, which will go to Reponse

        return ResponseEntity.ok(new CreateUserAccountResponse(msgSource.OK001, addedAccount.getId()));  //"Poprawnie utworzono konto użytkownika."
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount) {
        BigDecimal rechargeAmount = extractAmountToBigDecimal(amount);
        addRechargeAmountToUserAccountBalance(accountId, rechargeAmount);

        return ResponseEntity.ok(BasicResponse.of(msgSource.OK002));  //Operacja zakończyła się sukcesem.
    }

    //------private methods for createUserAccount()------
    private void validateCreateAccountRequest(CreateUserAccountRequest request) {
        if (isNullOrEmpty(request.getOwnerUsername())
                || isNullOrEmpty(request.getOwnerEmail())
                || isNull(request.getOwnerAge())) {
            throw new CommonBadRequestException(msgSource.ERR001); //err001=Dane wymagane do realizacji żądania są niekompletne.
        }
        if (isIncorrectEmail(request.getOwnerEmail())) {
            throw new CommonBadRequestException(msgSource.ERR002); //Podaj poprawny adres e-mail.
        }
        if (isIncorrectAge(request.getOwnerAge())) {
            throw new CommonBadRequestException(msgSource.ERR003); //Wiek powinien mieścić się w zakresie od 1 do 100.
        }
    }

    private void checkOwnerEmailAlreadyExists(String ownerEmail) {
        List<UserAccount> userAccounts = userAccountRepository.findByOwnerEmail(ownerEmail);
        if (!userAccounts.isEmpty()) {
            throw new CommonBadRequestException(msgSource.ERR004); //Konto o podanym adresie e-mail już istnieje.
        }
    }

    private UserAccount addUserAccountToUserRepository(CreateUserAccountRequest request) {
        UserAccount userAccount = new UserAccount(
                null, //id
                request.getOwnerEmail(),
                request.getOwnerUsername(),
                request.getOwnerAge(),
                new BigDecimal(0.0), //empty account
                LocalDateTime.now()   //date of adding account
        );

        return userAccountRepository.save(userAccount);
    }

    //------private methods for rechargeUserAccount()------
    private BigDecimal extractAmountToBigDecimal(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (NumberFormatException nfe) {
            throw new CommonBadRequestException(msgSource.ERR005); //Podaj poprawną kwotę doładowania.
        }
    }

    private void addRechargeAmountToUserAccountBalance(Long accountId, BigDecimal rechargeAmount) {
        Optional<UserAccount> userAccountData = userAccountRepository.findById(accountId);

        if (!userAccountData.isPresent()) {
            throw new CommonConflictException(msgSource.ERR006); //Konto użytkownika o danym id nie istnieje.
        }
        UserAccount accountData = userAccountData.get();
        accountData.setBalance(accountData.getBalance().add(rechargeAmount));
        userAccountRepository.save(accountData);
    }


}
