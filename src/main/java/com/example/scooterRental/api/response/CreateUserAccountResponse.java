package com.example.scooterRental.api.response;

import com.example.scooterRental.api.BasicResponse;

public class CreateUserAccountResponse extends BasicResponse {

    private Long accountId;

    public CreateUserAccountResponse() {
    }

    public CreateUserAccountResponse(String responseMsg, Long accountId) {
        super(responseMsg);
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
