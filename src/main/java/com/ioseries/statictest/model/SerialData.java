package com.ioseries.statictest.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SerialData {
    private String data;
    private ArrayList<String> serialPorts;

    SerialData() {
        this.data = "";
        this.serialPorts = new ArrayList();
    }

    public ArrayList<String> getSerialPorts() {
        return serialPorts;
    }

    public void setSerialPorts(ArrayList<String> serialPorts) {
        this.serialPorts = serialPorts;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}