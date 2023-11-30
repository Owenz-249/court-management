package com.example.courtreservationwebsite.Model;

import java.util.Date;

public class CourtAvailability {
    private int id;
    private Court court;
    private int kipTime;
    private Date dayAvailable;
    private int status;
    private String formattedTimeRange;

    public CourtAvailability() {}

    public CourtAvailability(int id, Court court, int kipTime, Date dayAvailable, int status) {
        this.id = id;
        this.court = court;
        this.kipTime = kipTime;
        this.dayAvailable = dayAvailable;
        this.status = status;
    }

    public String getFormattedTimeRange() {
        switch (kipTime) {
            case 1:
                return "7h-9h";
            case 2:
                return "9h-11h";
            case 3:
                return "14h-16h";
            case 4:
                return "16h-18h";
            case 5:
                return "18h-20h";
            default:
                return "Unknown";
        }
    }

    // Getters and setters


    public void setFormattedTimeRange(String formattedTimeRange) {
        this.formattedTimeRange = formattedTimeRange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public int getKipTime() {
        return kipTime;
    }

    public void setKipTime(int kipTime) {
        this.kipTime = kipTime;
    }

    public Date getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(Date dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
