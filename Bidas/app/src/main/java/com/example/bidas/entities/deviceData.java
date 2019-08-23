package com.example.bidas.entities;

public class deviceData {

    private String id;
    private String name;

    public deviceData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getDeviceId() {
        return id;
    }

    public void setDeviceId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return name;
    }

    public void setDeviceName(String name) {
        this.name = name;
    }


}
