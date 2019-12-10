package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("received")
    @Expose
    private List<Reservation> received = null;
    @SerializedName("modified")
    @Expose
    private ArrayList<Object> modified = null;
    @SerializedName("canceled")
    @Expose
    private ArrayList<Reservation> canceled = null;
    @SerializedName("arrivals")
    @Expose
    private ArrayList<Object> arrivals = null;
    @SerializedName("departures")
    @Expose
    private List<Object> departures = null;
    @SerializedName("stay")
    @Expose
    private ArrayList<Object> stay = null;
    @SerializedName("calendar")
    @Expose
    private ArrayList<Reservation> calendar = null;
    @SerializedName("reservations")
    @Expose
    private ArrayList<Object> reservations = null;
    @SerializedName("reservations_max_price")
    @Expose
    private String reservationsMaxPrice;
    @SerializedName("reservations_max_nights")
    @Expose
    private String reservationsMaxNights;
    @SerializedName("guests")
    @Expose
    private ArrayList<Object> guests = null;
    @SerializedName("guests_max_paid")
    @Expose
    private String guestsMaxPaid;
    @SerializedName("guests_max_nights")
    @Expose
    private String guestsMaxNights;
    @SerializedName("companies")
    @Expose
    private ArrayList<Company> companies = null;
    @SerializedName("invoices")
    @Expose
    private ArrayList<Object> invoices = null;
    @SerializedName("offers")
    @Expose
    private ArrayList<Object> offers = null;
    @SerializedName("changelog")
    @Expose
    private ArrayList<Changelog> changelog = null;
    @SerializedName("occupancy")
    @Expose
    private Occupancy occupancy;
    @SerializedName("rooms")
    @Expose
    private ArrayList<Room> rooms = null;
    @SerializedName("channels")
    @Expose
    private ArrayList<Channel> channels = null;
    @SerializedName("prices")
    @Expose
    private ArrayList<Price> prices = null;
    @SerializedName("restrictions")
    @Expose
    private ArrayList<Restriction> restrictions = null;
    @SerializedName("users")
    @Expose
    private ArrayList<DataUser> users = null;
    @SerializedName("pending_users")
    @Expose
    private String pendingUsers;

    public Data(Parcel in) {
        status = in.readString();
        received = in.createTypedArrayList(Reservation.CREATOR);
        canceled = in.createTypedArrayList(Reservation.CREATOR);
        calendar = in.createTypedArrayList(Reservation.CREATOR);
        reservationsMaxPrice = in.readString();
        reservationsMaxNights = in.readString();
        guestsMaxPaid = in.readString();
        guestsMaxNights = in.readString();
        changelog = in.createTypedArrayList(Changelog.CREATOR);
        rooms = in.createTypedArrayList(Room.CREATOR);
        channels = in.createTypedArrayList(Channel.CREATOR);
        restrictions = in.createTypedArrayList(Restriction.CREATOR);
        pendingUsers = in.readString();
    }

    public Data() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(received);
        dest.writeTypedList(canceled);
        dest.writeTypedList(calendar);
        dest.writeString(reservationsMaxPrice);
        dest.writeString(reservationsMaxNights);
        dest.writeString(guestsMaxPaid);
        dest.writeString(guestsMaxNights);
        dest.writeTypedList(changelog);
        dest.writeTypedList(rooms);
        dest.writeTypedList(channels);
        dest.writeTypedList(restrictions);
        dest.writeString(pendingUsers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getReceived() {
        return received;
    }

    public void setReceived(List<Reservation> received) {
        this.received = received;
    }

    public ArrayList<Object> getModified() {
        return modified;
    }

    public void setModified(ArrayList<Object> modified) {
        this.modified = modified;
    }

    public ArrayList<Reservation> getCanceled() {
        return canceled;
    }

    public void setCanceled(ArrayList<Reservation> canceled) {
        this.canceled = canceled;
    }

    public ArrayList<Object> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<Object> arrivals) {
        this.arrivals = arrivals;
    }

    public List<Object> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Object> departures) {
        this.departures = departures;
    }

    public ArrayList<Object> getStay() {
        return stay;
    }

    public void setStay(ArrayList<Object> stay) {
        this.stay = stay;
    }

    public ArrayList<Reservation> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Reservation> calendar) {
        this.calendar = calendar;
    }

    public ArrayList<Object> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Object> reservations) {
        this.reservations = reservations;
    }

    public String getReservationsMaxPrice() {
        return reservationsMaxPrice;
    }

    public void setReservationsMaxPrice(String reservationsMaxPrice) {
        this.reservationsMaxPrice = reservationsMaxPrice;
    }

    public String getReservationsMaxNights() {
        return reservationsMaxNights;
    }

    public void setReservationsMaxNights(String reservationsMaxNights) {
        this.reservationsMaxNights = reservationsMaxNights;
    }

    public ArrayList<Object> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Object> guests) {
        this.guests = guests;
    }

    public String getGuestsMaxPaid() {
        return guestsMaxPaid;
    }

    public void setGuestsMaxPaid(String guestsMaxPaid) {
        this.guestsMaxPaid = guestsMaxPaid;
    }

    public String getGuestsMaxNights() {
        return guestsMaxNights;
    }

    public void setGuestsMaxNights(String guestsMaxNights) {
        this.guestsMaxNights = guestsMaxNights;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public ArrayList<Object> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Object> invoices) {
        this.invoices = invoices;
    }

    public ArrayList<Object> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Object> offers) {
        this.offers = offers;
    }

    public ArrayList<Changelog> getChangelog() {
        return changelog;
    }

    public void setChangelog(ArrayList<Changelog> changelog) {
        this.changelog = changelog;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Occupancy occupancy) {
        this.occupancy = occupancy;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public ArrayList<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(ArrayList<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public ArrayList<DataUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<DataUser> users) {
        this.users = users;
    }

    public String getPendingUsers() {
        return pendingUsers;
    }

    public void setPendingUsers(String pendingUsers) {
        this.pendingUsers = pendingUsers;
    }
}
