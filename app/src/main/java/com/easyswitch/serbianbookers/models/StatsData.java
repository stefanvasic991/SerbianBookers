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
    private List<StatsRoom> rooms = null;
    @SerializedName("channels")
    @Expose
    private List<StatsChannel> channels = null;
    @SerializedName("channels_percentage")
    @Expose
    private List<ChannelPercentage> channelsPercentage = null;
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

    public List<StatsRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<StatsRoom> rooms) {
        this.rooms = rooms;
    }

    public List<StatsChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<StatsChannel> channels) {
        this.channels = channels;
    }

    public List<ChannelPercentage> getChannelsPercentage() {
        return channelsPercentage;
    }

    public void setChannelsPercentage(List<ChannelPercentage> channelsPercentage) {
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
