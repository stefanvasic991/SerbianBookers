package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PricePerDay implements Parcelable {

    private Double price;

    protected PricePerDay(Parcel in) {
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PricePerDay> CREATOR = new Creator<PricePerDay>() {
        @Override
        public PricePerDay createFromParcel(Parcel in) {
            return new PricePerDay(in);
        }

        @Override
        public PricePerDay[] newArray(int size) {
            return new PricePerDay[size];
        }
    };

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
