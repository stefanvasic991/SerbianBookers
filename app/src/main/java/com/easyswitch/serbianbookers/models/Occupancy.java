package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Occupancy {

    @SerializedName("today")
    @Expose
    private String today;
    @SerializedName("yesterday")
    @Expose
    private String yesterday;
    @SerializedName("last_week")
    @Expose
    private String lastWeek;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public String getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(String lastWeek) {
        this.lastWeek = lastWeek;
    }

}