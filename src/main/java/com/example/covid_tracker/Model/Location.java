package com.example.covid_tracker.Model;

public class Location {
    //
    private String state;
    private String country;
    private String lastestTotalCase;
    //

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastestTotalCase() {
        return lastestTotalCase;
    }

    public void setLastestTotalCase(String lastestTotalCase) {
        this.lastestTotalCase = lastestTotalCase;
    }
}
