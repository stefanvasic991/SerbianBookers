package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailabilityData implements Parcelable {

    @SerializedName("closed_arrival")
    @Expose
    private Integer closedArrival;
    @SerializedName("booked")
    @Expose
    private Integer booked;
    @SerializedName("max_stay_arrival")
    @Expose
    private Integer maxStayArrival;
    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("min_stay")
    @Expose
    private Integer minStay;
    @SerializedName("closed_departure")
    @Expose
    private String closedDeparture;
    @SerializedName("avail")
    @Expose
    private Integer avail;
    @SerializedName("max_stay")
    @Expose
    private Integer maxStay;
    @SerializedName("min_stay_arrival")
    @Expose
    private Integer minStayArrival;
    @SerializedName("no_ota")
    @Expose
    private Integer noOta;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("pricing_plan")
    @Expose
    private Double pricingPlan;
    @SerializedName("restriction_plan")
    @Expose
    private RestrictionPlan restrictionPlan;

    public AvailabilityData() {

    }


    protected AvailabilityData(Parcel in) {
        if (in.readByte() == 0) {
            closedArrival = null;
        } else {
            closedArrival = in.readInt();
        }
        if (in.readByte() == 0) {
            booked = null;
        } else {
            booked = in.readInt();
        }
        if (in.readByte() == 0) {
            maxStayArrival = null;
        } else {
            maxStayArrival = in.readInt();
        }
        if (in.readByte() == 0) {
            closed = null;
        } else {
            closed = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        if (in.readByte() == 0) {
            minStay = null;
        } else {
            minStay = in.readInt();
        }
        closedDeparture = in.readString();
        if (in.readByte() == 0) {
            avail = null;
        } else {
            avail = in.readInt();
        }
        if (in.readByte() == 0) {
            maxStay = null;
        } else {
            maxStay = in.readInt();
        }
        if (in.readByte() == 0) {
            minStayArrival = null;
        } else {
            minStayArrival = in.readInt();
        }
        if (in.readByte() == 0) {
            noOta = null;
        } else {
            noOta = in.readInt();
        }
        date = in.readString();
        day = in.readString();
        if (in.readByte() == 0) {
            pricingPlan = null;
        } else {
            pricingPlan = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (closedArrival == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(closedArrival);
        }
        if (booked == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(booked);
        }
        if (maxStayArrival == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxStayArrival);
        }
        if (closed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(closed);
        }
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        if (minStay == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minStay);
        }
        dest.writeString(closedDeparture);
        if (avail == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avail);
        }
        if (maxStay == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxStay);
        }
        if (minStayArrival == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minStayArrival);
        }
        if (noOta == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(noOta);
        }
        dest.writeString(date);
        dest.writeString(day);
        if (pricingPlan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(pricingPlan);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AvailabilityData> CREATOR = new Creator<AvailabilityData>() {
        @Override
        public AvailabilityData createFromParcel(Parcel in) {
            return new AvailabilityData(in);
        }

        @Override
        public AvailabilityData[] newArray(int size) {
            return new AvailabilityData[size];
        }
    };

    public Integer getClosedArrival() {
        return closedArrival;
    }

    public void setClosedArrival(Integer closedArrival) {
        this.closedArrival = closedArrival;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }

    public Integer getMaxStayArrival() {
        return maxStayArrival;
    }

    public void setMaxStayArrival(Integer maxStayArrival) {
        this.maxStayArrival = maxStayArrival;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer setPrice(Integer price) {
        this.price = price;
        return price;
    }

    public Integer getMinStay() {
        return minStay;
    }

    public void setMinStay(Integer minStay) {
        this.minStay = minStay;
    }

    public String getClosedDeparture() {
        return closedDeparture;
    }

    public void setClosedDeparture(String closedDeparture) {
        this.closedDeparture = closedDeparture;
    }

    public Integer getAvail() {
        return avail;
    }

    public void setAvail(Integer avail) {
        this.avail = avail;
    }

    public Integer getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(Integer maxStay) {
        this.maxStay = maxStay;
    }

    public Integer getMinStayArrival() {
        return minStayArrival;
    }

    public void setMinStayArrival(Integer minStayArrival) {
        this.minStayArrival = minStayArrival;
    }

    public Integer getNoOta() {
        return noOta;
    }

    public void setNoOta(Integer noOta) {
        this.noOta = noOta;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getPricingPlan() {
        return pricingPlan;
    }

    public void setPricingPlan(Double pricingPlan) {
        this.pricingPlan = pricingPlan;
    }

    public RestrictionPlan getRestrictionPlan() {
        return restrictionPlan;
    }

    public void setRestrictionPlan(RestrictionPlan restrictionPlan) {
        this.restrictionPlan = restrictionPlan;
    }

    public class RestrictionPlan {

        @SerializedName("closed_arrival")
        @Expose
        private Integer closedArrival;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("id_room")
        @Expose
        private Integer idRoom;
        @SerializedName("min_stay")
        @Expose
        private Integer minStay;
        @SerializedName("closed_departure")
        @Expose
        private Integer closedDeparture;
        @SerializedName("id_rplan")
        @Expose
        private Integer idRplan;
        @SerializedName("max_stay")
        @Expose
        private Integer maxStay;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("min_stay_arrival")
        @Expose
        private Integer minStayArrival;

        public Integer getClosedArrival() {
            return closedArrival;
        }

        public void setClosedArrival(Integer closedArrival) {
            this.closedArrival = closedArrival;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getIdRoom() {
            return idRoom;
        }

        public void setIdRoom(Integer idRoom) {
            this.idRoom = idRoom;
        }

        public Integer getMinStay() {
            return minStay;
        }

        public void setMinStay(Integer minStay) {
            this.minStay = minStay;
        }

        public Integer getClosedDeparture() {
            return closedDeparture;
        }

        public void setClosedDeparture(Integer closedDeparture) {
            this.closedDeparture = closedDeparture;
        }

        public Integer getIdRplan() {
            return idRplan;
        }

        public void setIdRplan(Integer idRplan) {
            this.idRplan = idRplan;
        }

        public Integer getMaxStay() {
            return maxStay;
        }

        public void setMaxStay(Integer maxStay) {
            this.maxStay = maxStay;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getMinStayArrival() {
            return minStayArrival;
        }

        public void setMinStayArrival(Integer minStayArrival) {
            this.minStayArrival = minStayArrival;
        }
    }
}

