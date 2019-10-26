package com.example.scooterRental.service;

import com.example.scooterRental.common.MsgSource;

public abstract class AbstractCommonService {

    protected MsgSource msgSource;

    public AbstractCommonService(MsgSource msgSource) {
        this.msgSource = msgSource;
    }
}
