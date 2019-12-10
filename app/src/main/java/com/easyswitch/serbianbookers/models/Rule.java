package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rule {

    @SerializedName("closed_arrival")
    @Expose
    private Integer closedArrival;
    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("min_stay")
    @Expose
    private Integer minStay;
    @SerializedName("closed_departure")
    @Expose
    private Integer closedDeparture;
    @SerializedName("max_stay")
    @Expose
    private Integer maxStay;
    @SerializedName("min_stay_arrival")
    @Expose
    private Integer minStayArrival;

    public Integer getClosedArrival() {
        return closedArrival;
    }

    public void setClosedArrival(Integer closedArrival) {
        this.closedArrival = closedArrival;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getMinStay() {
        return minStay;
    }

    public void setMinStay(Integer minStay) {
        this.minStay = minStay;
    }

    public Integer getClosedDeparture() {
        return closedDeparture;
    }

    public void setClosedDeparture(Integer closedDeparture) {
        this.closedDeparture = closedDeparture;
    }

    public Integer getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(Integer maxStay) {
        this.maxStay = maxStay;
    }

    public Integer getMinStayArrival() {
        return minStayArrival;
    }

    public void setMinStayArrival(Integer minStayArrival) {
        this.minStayArrival = minStayArrival;
    }

}