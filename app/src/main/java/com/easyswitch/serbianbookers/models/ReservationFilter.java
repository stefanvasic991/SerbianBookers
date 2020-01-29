package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReservationFilter {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("order_by")
    @Expose
    private String orderBy;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("date_received_from")
    @Expose
    private String dateReceivedFrom;
    @SerializedName("date_received_to")
    @Expose
    private String dateReceivedTo;
    @SerializedName("date_arrival_from")
    @Expose
    private String dateArrivalFrom;
    @SerializedName("date_arrival_to")
    @Expose
    private String dateArrivalTo;
    @SerializedName("date_departure_from")
    @Expose
    private String dateDepartureFrom;
    @SerializedName("date_departure_to")
    @Expose
    private String dateDepartureTo;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("reservations_room")
    @Expose
    private String room;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reservations")
    @Expose
    private List<Reservation> filterReservationList;

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDateReceivedFrom() {
        return dateReceivedFrom;
    }

    public void setDateReceivedFrom(String dateReceivedFrom) {
        this.dateReceivedFrom = dateReceivedFrom;
    }

    public String getDateReceivedTo() {
        return dateReceivedTo;
    }

    public void setDateReceivedTo(String dateReceivedTo) {
        this.dateReceivedTo = dateReceivedTo;
    }

    public String getDateArrivalFrom() {
        return dateArrivalFrom;
    }

    public void setDateArrivalFrom(String dateArrivalFrom) {
        this.dateArrivalFrom = dateArrivalFrom;
    }

    public String getDateArrivalTo() {
        return dateArrivalTo;
    }

    public void setDateArrivalTo(String dateArrivalTo) {
        this.dateArrivalTo = dateArrivalTo;
    }

    public String getDateDepartureFrom() {
        return dateDepartureFrom;
    }

    public void setDateDepartureFrom(String dateDepartureFrom) {
        this.dateDepartureFrom = dateDepartureFrom;
    }

    public String getDateDepartureTo() {
        return dateDepartureTo;
    }

    public void setDateDepartureTo(String dateDepartureTo) {
        this.dateDepartureTo = dateDepartureTo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getFilterReservationList() {
        return filterReservationList;
    }

    public void setFilterReservationList(List<Reservation> filterReservationList) {
        this.filterReservationList = filterReservationList;
    }
}
