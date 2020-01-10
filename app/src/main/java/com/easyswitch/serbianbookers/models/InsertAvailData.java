package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertAvailData {

    @SerializedName("closed_arrival")
    @Expose
    private Object closedArrival;
    @SerializedName("booked")
    @Expose
    private Object booked;
    @SerializedName("max_stay_arrival")
    @Expose
    private Object maxStayArrival;
    @SerializedName("closed")
    @Expose
    private Object closed;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("min_stay")
    @Expose
    private Object minStay;
    @SerializedName("closed_departure")
    @Expose
    private Object closedDeparture;
    @SerializedName("avail")
    @Expose
    private Object avail;
    @SerializedName("max_stay")
    @Expose
    private Object maxStay;
    @SerializedName("min_stay_arrival")
    @Expose
    private Object minStayArrival;
    @SerializedName("no_ota")
    @Expose
    private Object noOta;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("day")
    @Expose
    private Object day;

    public Object getClosedArrival() {
        return closedArrival;
    }

    public void setClosedArrival(Object closedArrival) {
        this.closedArrival = closedArrival;
    }

    public Object getBooked() {
        return booked;
    }

    public void setBooked(Object booked) {
        this.booked = booked;
    }

    public Object getMaxStayArrival() {
        return maxStayArrival;
    }

    public void setMaxStayArrival(Object maxStayArrival) {
        this.maxStayArrival = maxStayArrival;
    }

    public Object getClosed() {
        return closed;
    }

    public void setClosed(Object closed) {
        this.closed = closed;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getMinStay() {
        return minStay;
    }

    public void setMinStay(Object minStay) {
        this.minStay = minStay;
    }

    public Object getClosedDeparture() {
        return closedDeparture;
    }

    public void setClosedDeparture(Object closedDeparture) {
        this.closedDeparture = closedDeparture;
    }

    public Object getAvail() {
        return avail;
    }

    public void setAvail(Object avail) {
        this.avail = avail;
    }

    public Object getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(Object maxStay) {
        this.maxStay = maxStay;
    }

    public Object getMinStayArrival() {
        return minStayArrival;
    }

    public void setMinStayArrival(Object minStayArrival) {
        this.minStayArrival = minStayArrival;
    }

    public Object getNoOta() {
        return noOta;
    }

    public void setNoOta(Object noOta) {
        this.noOta = noOta;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getDay() {
        return day;
    }

    public void setDay(Object day) {
        this.day = day;
    }
}
