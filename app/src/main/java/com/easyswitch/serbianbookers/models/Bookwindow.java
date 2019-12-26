package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bookwindow {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("income")
    @Expose
    private Double income;
    @SerializedName("nights")
    @Expose
    private Integer nights;
    @SerializedName("avg_income")
    @Expose
    private Double avgIncome;
    @SerializedName("avg_nights")
    @Expose
    private Double avgNights;
    @SerializedName("canceled_percentage")
    @Expose
    private Double canceledPercentage;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
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

    public Double getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Double avgIncome) {
        this.avgIncome = avgIncome;
    }

    public Double getAvgNights() {
        return avgNights;
    }

    public void setAvgNights(Double avgNights) {
        this.avgNights = avgNights;
    }

    public Double getCanceledPercentage() {
        return canceledPercentage;
    }

    public void setCanceledPercentage(Double canceledPercentage) {
        this.canceledPercentage = canceledPercentage;
    }
}
