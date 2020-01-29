package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestrictionPlan {

    @SerializedName("closed_arrival")
    @Expose
    private Integer closedArrival;
    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("id_room")
    @Expose
    private Integer idRoom;
    @SerializedName("min_stay")
    @Expose
    private Integer minStay;
    @SerializedName("closed_departure")
    @Expose
    private Integer closedDeparture;
    @SerializedName("id_rplan")
    @Expose
    private Integer idRplan;
    @SerializedName("max_stay")
    @Expose
    private Integer maxStay;
    @SerializedName("date")
    @Expose
    private String date;
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

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
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

    public Integer getIdRplan() {
        return idRplan;
    }

    public void setIdRplan(Integer idRplan) {
        this.idRplan = idRplan;
    }

    public Integer getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(Integer maxStay) {
        this.maxStay = maxStay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMinStayArrival() {
        return minStayArrival;
    }

    public void setMinStayArrival(Integer minStayArrival) {
        this.minStayArrival = minStayArrival;
    }
}