package com.example.scooterRental.exception;

import com.example.scooterRental.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException {

    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
