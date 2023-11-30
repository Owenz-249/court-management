package com.example.courtreservationwebsite.Model;

public class SearchForm {

    private int kipTime;
    private String courtType;
    private String dayBegin;
    private String dayEnd;

    public SearchForm() {}

    public int getKipTime() {
        return kipTime;
    }

    public void setKipTime(int kipTime) {
        this.kipTime = kipTime;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }

    public String getDayBegin() {
        return dayBegin;
    }

    public void setDayBegin(String dayBegin) {
        this.dayBegin = dayBegin;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }
}
