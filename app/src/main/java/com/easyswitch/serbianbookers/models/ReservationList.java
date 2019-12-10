package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReservationList implements Parcelable {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("account")
    @Expose
    private String account;
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

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reservations")
    @Expose
    private ArrayList<Reservation> reservations = null;
    @SerializedName("reservations_max_price")
    @Expose
    private String reservationsMaxPrice;
    @SerializedName("reservations_max_nights")
    @Expose
    private String reservationsMaxNights;

    protected ReservationList(Parcel in) {
        key = in.readString();
        lcode = in.readString();
        account = in.readString();
        reservationsDfrom = in.readString();
        reservationsDto = in.readString();
        reservationsOrderBy = in.readString();
        reservationsFilterBy = in.readString();
        reservationsOrderType = in.readString();
        status = in.readString();
        reservations = in.createTypedArrayList(Reservation.CREATOR);
        reservationsMaxPrice = in.readString();
        reservationsMaxNights = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(lcode);
        dest.writeString(account);
        dest.writeString(reservationsDfrom);
        dest.writeString(reservationsDto);
        dest.writeString(reservationsOrderBy);
        dest.writeString(reservationsFilterBy);
        dest.writeString(reservationsOrderType);
        dest.writeString(status);
        dest.writeTypedList(reservations);
        dest.writeString(reservationsMaxPrice);
        dest.writeString(reservationsMaxNights);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReservationList> CREATOR = new Creator<ReservationList>() {
        @Override
        public ReservationList createFromParcel(Parcel in) {
            return new ReservationList(in);
        }

        @Override
        public ReservationList[] newArray(int size) {
            return new ReservationList[size];
        }
    };

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
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

}
