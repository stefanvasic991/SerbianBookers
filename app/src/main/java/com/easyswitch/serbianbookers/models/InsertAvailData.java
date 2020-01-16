package com.easyswitch.serbianbookers.models;

import com.easyswitch.serbianbookers.models.insert.Avail;
import com.easyswitch.serbianbookers.models.insert.Closed;
import com.easyswitch.serbianbookers.models.insert.ClosedInOut;
import com.easyswitch.serbianbookers.models.insert.MaxStay;
import com.easyswitch.serbianbookers.models.insert.MinStay;
import com.easyswitch.serbianbookers.models.insert.MinStayArr;
import com.easyswitch.serbianbookers.models.insert.NoOta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertAvailData {

    @SerializedName("closed_arrival")
    @Expose
    private Closed closedArrival;
    @SerializedName("booked")
    @Expose
    private Object booked;
    @SerializedName("max_stay_arrival")
    @Expose
    private Object maxStayArrival;
    @SerializedName("closed")
    @Expose
    private Closed closed;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("min_stay")
    @Expose
    private MinStay min_stay;
    @SerializedName("closed_departure")
    @Expose
    private Closed closedDeparture;
    @SerializedName("avail")
    @Expose
    private Avail avail;
    @SerializedName("max_stay")
    @Expose
    private MaxStay max_stay;
    @SerializedName("min_stay_arrival")
    @Expose
    private MinStayArr min_stay_arrival;
    @SerializedName("no_ota")
    @Expose
    private NoOta no_ota;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("day")
    @Expose
    private Object day;

    public Closed getClosedArrival() {
        return closedArrival;
    }

    public void setClosedArrival(Closed closedArrival) {
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

    public Closed getClosed() {
        return closed;
    }

    public void setClosed(Closed closed) {
        this.closed = closed;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public MinStay getMin_stay() {
        return min_stay;
    }

    public void setMin_stay(MinStay min_stay) {
        this.min_stay = min_stay;
    }

    public Closed getClosedDeparture() {
        return closedDeparture;
    }

    public void setClosedDeparture(Closed closedDeparture) {
        this.closedDeparture = closedDeparture;
    }

    public Avail getAvail() {
        return avail;
    }

    public void setAvail(Avail avail) {
        this.avail = avail;
    }

    public MaxStay getMax_stay() {
        return max_stay;
    }

    public void setMax_stay(MaxStay max_stay) {
        this.max_stay = max_stay;
    }

    public MinStayArr getMin_stay_arrival() {
        return min_stay_arrival;
    }

    public void setMin_stay_arrival(MinStayArr min_stay_arrival) {
        this.min_stay_arrival = min_stay_arrival;
    }

    public NoOta getNo_ota() {
        return no_ota;
    }

    public void setNo_ota(NoOta no_ota) {
        this.no_ota = no_ota;
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
