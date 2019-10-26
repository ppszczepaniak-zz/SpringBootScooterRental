package com.example.scooterRental.api.response;

import com.example.scooterRental.api.BasicResponse;

public class AddScooterResponse extends BasicResponse {

    private long scooterId;

    public AddScooterResponse() {
    }

    public AddScooterResponse(String responseMsg, long scooterId) {
        super(responseMsg);
        this.scooterId = scooterId;
    }

    public long getScooterId() {
        return scooterId;
    }

    public void setScooterId(long scooterId) {
        this.scooterId = scooterId;
    }
}
