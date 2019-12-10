package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ctype")
    @Expose
    private String ctype;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;

    protected Channel(Parcel in) {
        id = in.readString();
        ctype = in.readString();
        name = in.readString();
        tag = in.readString();
        logo = in.readString();
        commission = in.readString();
        hotelId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(ctype);
        dest.writeString(name);
        dest.writeString(tag);
        dest.writeString(logo);
        dest.writeString(commission);
        dest.writeString(hotelId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

}