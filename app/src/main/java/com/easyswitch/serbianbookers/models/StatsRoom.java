package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsRoom {

    @SerializedName("count")
    @Expose
    private Integer count;
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
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("shortname")
    @Expose
    private String shortName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
