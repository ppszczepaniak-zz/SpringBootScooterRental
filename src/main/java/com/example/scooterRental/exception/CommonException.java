package com.example.scooterRental.exception;

import com.example.scooterRental.common.ConstErrorMsg;

public class CommonException extends RuntimeException {
//parent class for my two exceptions

    private ConstErrorMsg constErrorMsg;

    public CommonException(ConstErrorMsg constErrorMsg) {
        this.constErrorMsg = constErrorMsg;
    }

    public ConstErrorMsg getConstErrorMsg() {
        return constErrorMsg;
    }

}
