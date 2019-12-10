package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBody {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("news_order_by")
    @Expose
    private String newsOrderBy;
    @SerializedName("news_order_type")
    @Expose
    private String newsOrderType;
    @SerializedName("news_dfrom")
    @Expose
    private String newsDfrom;
    @SerializedName("events_dfrom")
    @Expose
    private String eventsDfrom;
    @SerializedName("events_dto")
    @Expose
    private String eventsDto;
    @SerializedName("calendar_dfrom")
    @Expose
    private String calendarDfrom;
    @SerializedName("calendar_dto")
    @Expose
    private String calendarDto;
    @SerializedName("reservations_dfrom")
    @Expose
    private String reservationsDfrom;
    @SerializedName("reservations_dto")
    @Expose
    private String reservationsDto;
    @SerializedName("reservations_order_by")
    @Expose
    private String reservationsOrderBy;
    @SerializedName("reservations_filter_by")
    @Expose
    private String reservationsFilterBy;
    @SerializedName("reservations_order_type")
    @Expose
    private String reservationsOrderType;
    @SerializedName("guests_order_by")
    @Expose
    private String guestsOrderBy;
    @SerializedName("guests_order_type")
    @Expose
    private String guestsOrderType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNewsOrderBy() {
        return newsOrderBy;
    }

    public void setNewsOrderBy(String newsOrderBy) {
        this.newsOrderBy = newsOrderBy;
    }

    public String getNewsOrderType() {
        return newsOrderType;
    }

    public void setNewsOrderType(String newsOrderType) {
        this.newsOrderType = newsOrderType;
    }

    public String getNewsDfrom() {
        return newsDfrom;
    }

    public void setNewsDfrom(String newsDfrom) {
        this.newsDfrom = newsDfrom;
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

    public String getCalendarDfrom() {
        return calendarDfrom;
    }

    public void setCalendarDfrom(String calendarDfrom) {
        this.calendarDfrom = calendarDfrom;
    }

    public String getCalendarDto() {
        return calendarDto;
    }

    public void setCalendarDto(String calendarDto) {
        this.calendarDto = calendarDto;
    }

    public String getReservationsDfrom() {
        return reservationsDfrom;
    }

    public void setReservationsDfrom(String reservationsDfrom) {
        this.reservationsDfrom = reservationsDfrom;
    }

    public String getReservationsDto() {
        return reservationsDto;
    }

    public void setReservationsDto(String reservationsDto) {
        this.reservationsDto = reservationsDto;
    }

    public String getReservationsOrderBy() {
        return reservationsOrderBy;
    }

    public void setReservationsOrderBy(String reservationsOrderBy) {
        this.reservationsOrderBy = reservationsOrderBy;
    }

    public String getReservationsFilterBy() {
        return reservationsFilterBy;
    }

    public void setReservationsFilterBy(String reservationsFilterBy) {
        this.reservationsFilterBy = reservationsFilterBy;
    }

    public String getReservationsOrderType() {
        return reservationsOrderType;
    }

    public void setReservationsOrderType(String reservationsOrderType) {
        this.reservationsOrderType = reservationsOrderType;
    }

    public String getGuestsOrderBy() {
        return guestsOrderBy;
    }

    public void setGuestsOrderBy(String guestsOrderBy) {
        this.guestsOrderBy = guestsOrderBy;
    }

    public String getGuestsOrderType() {
        return guestsOrderType;
    }

    public void setGuestsOrderType(String guestsOrderType) {
        this.guestsOrderType = guestsOrderType;
    }

    @Override
    public String toString() {
        return "DataBody{" +
                "key='" + key + '\'' +
                ", lcode='" + lcode + '\'' +
                ", account='" + account + '\'' +
                ", newsOrderBy='" + newsOrderBy + '\'' +
                ", newsOrderType='" + newsOrderType + '\'' +
                ", newsDfrom='" + newsDfrom + '\'' +
                ", eventsDfrom='" + eventsDfrom + '\'' +
                ", eventsDto='" + eventsDto + '\'' +
                ", calendarDfrom='" + calendarDfrom + '\'' +
                ", calendarDto='" + calendarDto + '\'' +
                ", reservationsDfrom='" + reservationsDfrom + '\'' +
                ", reservationsDto='" + reservationsDto + '\'' +
                ", reservationsOrderBy='" + reservationsOrderBy + '\'' +
                ", reservationsFilterBy='" + reservationsFilterBy + '\'' +
                ", reservationsOrderType='" + reservationsOrderType + '\'' +
                ", guestsOrderBy='" + guestsOrderBy + '\'' +
                ", guestsOrderType='" + guestsOrderType + '\'' +
                '}';
    }
}
