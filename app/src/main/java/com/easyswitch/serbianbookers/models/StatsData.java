package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatsData {

    @SerializedName("months")
    @Expose
    private List<Month> months = null;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;
    @SerializedName("channels_percentage")
    @Expose
    private List<Object> channelsPercentage = null;
    @SerializedName("countries_percentage")
    @Expose
    private List<CountriesPercentage> countriesPercentage = null;
    @SerializedName("bookwindow")
    @Expose
    private List<Bookwindow> bookwindow = null;

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Object> getChannelsPercentage() {
        return channelsPercentage;
    }

    public void setChannelsPercentage(List<Object> channelsPercentage) {
        this.channelsPercentage = channelsPercentage;
    }

    public List<CountriesPercentage> getCountriesPercentage() {
        return countriesPercentage;
    }

    public void setCountriesPercentage(List<CountriesPercentage> countriesPercentage) {
        this.countriesPercentage = countriesPercentage;
    }

    public List<Bookwindow> getBookwindow() {
        return bookwindow;
    }

    public void setBookwindow(List<Bookwindow> bookwindow) {
        this.bookwindow = bookwindow;
    }
}
