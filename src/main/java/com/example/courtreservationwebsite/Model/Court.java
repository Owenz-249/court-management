package com.example.courtreservationwebsite.Model;

import java.util.ArrayList;
import java.util.List;

public class Court {
    private int id;
    private String name;
    private String type;
    private int fee;
    private List<CourtAvailability> availabilityList;

    public Court() {
        this.availabilityList = new ArrayList<>();
    }

    public Court(int id, String name, String type, int fee) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.fee = fee;
        this.availabilityList = new ArrayList<>();
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public List<CourtAvailability> getAvailabilityList() {
        if (availabilityList == null) {
            availabilityList = new ArrayList<>();
        }
        return availabilityList;
    }

    public void setAvailabilityList(List<CourtAvailability> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
