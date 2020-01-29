package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restriction {

    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("closed_arrival")
    @Expose
    private Integer closedArrival;
    @SerializedName("closed_departure")
    @Expose
    private Integer closedDeparture;
    @SerializedName("min_stay")
    @Expose
    private Integer minStay;
    @SerializedName("min_stay_arrival")
    @Expose
    private Integer minStayArr;
    @SerializedName("max_stay")
    @Expose
    private Integer maxStay;

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getClosedArrival() {
        return closedArrival;
    }

    public void setClosedArrival(Integer closedArrival) {
        this.closedArrival = closedArrival;
    }

    public Integer getClosedDeparture() {
        return closedDeparture;
    }

    public void setClosedDeparture(Integer closedDeparture) {
        this.closedDeparture = closedDeparture;
    }

    public Integer getMinStay() {
        return minStay;
    }

    public void setMinStay(Integer minStay) {
        this.minStay = minStay;
    }

    public Integer getMinStayArr() {
        return minStayArr;
    }

    public void setMinStayArr(Integer minStayArr) {
        this.minStayArr = minStayArr;
    }

    public Integer getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(Integer maxStay) {
        this.maxStay = maxStay;
    }
}
