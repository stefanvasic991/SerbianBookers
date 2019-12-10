package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search implements Parcelable {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("reservations")
    @Expose
    private List<Reservation> reservationList;
    @SerializedName("guests")
    @Expose
    private List<Guest> guests = null;
    @SerializedName("companies")
    @Expose
    private List<Object> companies = null;
    @SerializedName("invoices")
    @Expose
    private List<Object> invoices = null;
    @SerializedName("offers")
    @Expose
    private List<Object> offers = null;

    public Search(Parcel in) {
        key = in.readString();
        account = in.readString();
        lcode = in.readString();
        keyword = in.readString();
        reservationList = in.createTypedArrayList(Reservation.CREATOR);
        guests = in.createTypedArrayList(Guest.CREATOR);
    }

    public Search() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(account);
        dest.writeString(lcode);
        dest.writeString(keyword);
        dest.writeTypedList(reservationList);
        dest.writeTypedList(guests);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Object> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Object> companies) {
        this.companies = companies;
    }

    public List<Object> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Object> invoices) {
        this.invoices = invoices;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }
}
