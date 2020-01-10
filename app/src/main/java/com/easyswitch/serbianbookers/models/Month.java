package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Month implements Parcelable {

    @SerializedName("occupancy")
    @Expose
    private Double occupancy;
    @SerializedName("income")
    @Expose
    private Double income;
    @SerializedName("nights")
    @Expose
    private Integer nights;
    @SerializedName("max_income")
    @Expose
    private Double maxIncome;
    @SerializedName("max_income_guest")
    @Expose
    private String maxIncomeGuest;
    @SerializedName("avg_income")
    @Expose
    private Double avgIncome;
    @SerializedName("max_nights")
    @Expose
    private Integer maxNights;
    @SerializedName("max_nights_guest")
    @Expose
    private String maxNightsGuest;
    @SerializedName("avg_nights")
    @Expose
    private Double avgNights;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("month")
    @Expose
    private Integer month;
    @SerializedName("year")
    @Expose
    private Integer year;

    protected Month(Parcel in) {
        if (in.readByte() == 0) {
            occupancy = null;
        } else {
            occupancy = in.readDouble();
        }
        if (in.readByte() == 0) {
            income = null;
        } else {
            income = in.readDouble();
        }
        if (in.readByte() == 0) {
            nights = null;
        } else {
            nights = in.readInt();
        }
        if (in.readByte() == 0) {
            maxIncome = null;
        } else {
            maxIncome = in.readDouble();
        }
        maxIncomeGuest = in.readString();
        if (in.readByte() == 0) {
            avgIncome = null;
        } else {
            avgIncome = in.readDouble();
        }
        if (in.readByte() == 0) {
            maxNights = null;
        } else {
            maxNights = in.readInt();
        }
        maxNightsGuest = in.readString();
        if (in.readByte() == 0) {
            avgNights = null;
        } else {
            avgNights = in.readDouble();
        }
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
        if (in.readByte() == 0) {
            month = null;
        } else {
            month = in.readInt();
        }
        if (in.readByte() == 0) {
            year = null;
        } else {
            year = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (occupancy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(occupancy);
        }
        if (income == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(income);
        }
        if (nights == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nights);
        }
        if (maxIncome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(maxIncome);
        }
        dest.writeString(maxIncomeGuest);
        if (avgIncome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(avgIncome);
        }
        if (maxNights == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxNights);
        }
        dest.writeString(maxNightsGuest);
        if (avgNights == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(avgNights);
        }
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(count);
        }
        if (month == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(month);
        }
        if (year == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(year);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Month> CREATOR = new Creator<Month>() {
        @Override
        public Month createFromParcel(Parcel in) {
            return new Month(in);
        }

        @Override
        public Month[] newArray(int size) {
            return new Month[size];
        }
    };

    public Double getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Double occupancy) {
        this.occupancy = occupancy;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Double getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(Double maxIncome) {
        this.maxIncome = maxIncome;
    }

    public String getMaxIncomeGuest() {
        return maxIncomeGuest;
    }

    public void setMaxIncomeGuest(String maxIncomeGuest) {
        this.maxIncomeGuest = maxIncomeGuest;
    }

    public Double getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Double avgIncome) {
        this.avgIncome = avgIncome;
    }

    public Integer getMaxNights() {
        return maxNights;
    }

    public void setMaxNights(Integer maxNights) {
        this.maxNights = maxNights;
    }

    public String getMaxNightsGuest() {
        return maxNightsGuest;
    }

    public void setMaxNightsGuest(String maxNightsGuest) {
        this.maxNightsGuest = maxNightsGuest;
    }

    public Double getAvgNights() {
        return avgNights;
    }

    public void setAvgNights(Double avgNights) {
        this.avgNights = avgNights;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}