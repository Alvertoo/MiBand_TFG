package com.example.bidas.entities;

import java.util.Date;

public class deviceInfo {
    private String deviceId;
    private Date date;
    private int steps;

    public deviceInfo(String deviceId, Date date) {
        this.deviceId = deviceId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
