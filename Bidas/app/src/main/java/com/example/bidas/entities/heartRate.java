package com.example.bidas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class heartRate {
    private Date date;
    private List rate;

    public heartRate(Date date) {
        this.date = date;
        this.rate = new ArrayList();
    }

    public Date getDate() {
        return date;
    }

    public List getSteps() {
        return rate;
    }

    public void setSteps(int rate) {
        this.rate.add(rate);
    }
}
