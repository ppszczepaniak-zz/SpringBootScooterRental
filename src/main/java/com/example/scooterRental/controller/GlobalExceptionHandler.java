package com.example.scooterRental.controller;

import com.example.scooterRental.api.BasicResponse;
import com.example.scooterRental.exception.CommonBadRequestException;
import com.example.scooterRental.exception.CommonConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
//@ControllerAdvice – used to create exception service for whole app (we must define methods for exceptions with @ExceptionHandler
public class GlobalExceptionHandler {

    @ExceptionHandler
    // @ExceptionHandler – catches exception and maps it into HTTP status (like 404)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    BasicResponse handleBadRequestException(CommonBadRequestException exception) {
        return BasicResponse.ofError(
                exception.getConstErrorMsg().getErrorCode(),
                exception.getConstErrorMsg().getErrorMsg()
        );
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    BasicResponse handleConflictException(CommonConflictException exception) {
        return BasicResponse.ofError(
                exception.getConstErrorMsg().getErrorCode(),
                exception.getConstErrorMsg().getErrorMsg()
        );
    }
}
