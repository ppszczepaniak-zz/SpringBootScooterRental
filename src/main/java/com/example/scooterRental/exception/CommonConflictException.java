package com.example.scooterRental.exception;

import com.example.scooterRental.common.ConstErrorMsg;

public class CommonConflictException extends CommonException{

    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
