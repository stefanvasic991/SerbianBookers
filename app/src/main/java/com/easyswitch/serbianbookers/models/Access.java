package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Access implements Parcelable {

    @SerializedName("reservations")
    @Expose
    private String reservations;
    @SerializedName("guests")
    @Expose
    private String guests;
    @SerializedName("invoices")
    @Expose
    private String invoices;
    @SerializedName("prices")
    @Expose
    private String prices;
    @SerializedName("restrictions")
    @Expose
    private String restrictions;
    @SerializedName("avail")
    @Expose
    private String avail;
    @SerializedName("statistics")
    @Expose
    private String statistics;
    @SerializedName("rooms")
    @Expose
    private String rooms;
    @SerializedName("channels")
    @Expose
    private String channels;
    @SerializedName("changelog")
    @Expose
    private String changelog;

    protected Access(Parcel in) {
        reservations = in.readString();
        guests = in.readString();
        invoices = in.readString();
        prices = in.readString();
        restrictions = in.readString();
        avail = in.readString();
        statistics = in.readString();
        rooms = in.readString();
        channels = in.readString();
        changelog = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reservations);
        dest.writeString(guests);
        dest.writeString(invoices);
        dest.writeString(prices);
        dest.writeString(restrictions);
        dest.writeString(avail);
        dest.writeString(statistics);
        dest.writeString(rooms);
        dest.writeString(channels);
        dest.writeString(changelog);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Access> CREATOR = new Creator<Access>() {
        @Override
        public Access createFromParcel(Parcel in) {
            return new Access(in);
        }

        @Override
        public Access[] newArray(int size) {
            return new Access[size];
        }
    };

    public String getReservations() {
        return reservations;
    }

    public void setReservations(String reservations) {
        this.reservations = reservations;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getInvoices() {
        return invoices;
    }

    public void setInvoices(String invoices) {
        this.invoices = invoices;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }
}
