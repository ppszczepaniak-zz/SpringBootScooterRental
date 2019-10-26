package com.example.scooterRental.controller;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.api.request.CreateUserAccountRequest;
import com.example.scooterRental.api.response.CreateUserAccountResponse;
import com.example.scooterRental.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-account")
public class UserAccountController {

    private UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    //POST /user-account/create - żądanie odpowiadać będzie za utworzenie konta użytkownika w systemie.
    //PUT /user-account/{accountId}/recharge - żądanie będzie pozwalało na doładowanie konta użytkownika o podanym id.

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(
            @RequestBody CreateUserAccountRequest request
    ) {
        return null;
    }


    @PutMapping(value = "/{accountId}/recharge", produces = "application/json")
    public ResponseEntity<BasicResponse> rechargeUserAccount(
            @PathVariable Long accountId,
            @RequestParam String amount
    ) {
        return null;
    }


}
