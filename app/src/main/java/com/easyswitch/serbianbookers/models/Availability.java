package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availability implements Parcelable {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("dfrom")
    @Expose
    private String dfrom;
    @SerializedName("dto")
    @Expose
    private String dto;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("avail")
    @Expose
    private AvailabilityList availabilityList;

    public Availability(Parcel in) {
        key = in.readString();
        account = in.readString();
        lcode = in.readString();
        dfrom = in.readString();
        dto = in.readString();
        status = in.readString();
    }

    public Availability() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(account);
        dest.writeString(lcode);
        dest.writeString(dfrom);
        dest.writeString(dto);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Availability> CREATOR = new Creator<Availability>() {
        @Override
        public Availability createFromParcel(Parcel in) {
            return new Availability(in);
        }

        @Override
        public Availability[] newArray(int size) {
            return new Availability[size];
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

    public String getDfrom() {
        return dfrom;
    }

    public void setDfrom(String dfrom) {
        this.dfrom = dfrom;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AvailabilityList getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(AvailabilityList availabilityList) {
        this.availabilityList = availabilityList;
    }
}
