package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("events_dfrom")
    @Expose
    private String eventsDfrom;
    @SerializedName("events_dto")
    @Expose
    private String eventsDto;


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("arrivals")
    @Expose
    private List<Reservation> arrivals = null;
    @SerializedName("departures")
    @Expose
    private List<Reservation> departures = null;
    @SerializedName("stay")
    @Expose
    private List<Reservation> stay = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getEventsDfrom() {
        return eventsDfrom;
    }

    public void setEventsDfrom(String eventsDfrom) {
        this.eventsDfrom = eventsDfrom;
    }

    public String getEventsDto() {
        return eventsDto;
    }

    public void setEventsDto(String eventsDto) {
        this.eventsDto = eventsDto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Reservation> arrivals) {
        this.arrivals = arrivals;
    }

    public List<Reservation> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Reservation> departures) {
        this.departures = departures;
    }

    public List<Reservation> getStay() {
        return stay;
    }

    public void setStay(List<Reservation> stay) {
        this.stay = stay;
    }
}
