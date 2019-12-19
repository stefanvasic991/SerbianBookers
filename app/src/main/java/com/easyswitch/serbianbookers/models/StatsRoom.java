package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsRoom {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("income")
    @Expose
    private Integer income;
    @SerializedName("nights")
    @Expose
    private Integer nights;
    @SerializedName("avg_income")
    @Expose
    private Integer avgIncome;
    @SerializedName("avg_nights")
    @Expose
    private Integer avgNights;
    @SerializedName("id")
    @Expose
    private String id;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Integer getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Integer avgIncome) {
        this.avgIncome = avgIncome;
    }

    public Integer getAvgNights() {
        return avgNights;
    }

    public void setAvgNights(Integer avgNights) {
        this.avgNights = avgNights;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
