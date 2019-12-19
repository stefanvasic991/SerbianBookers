package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Month {

    @SerializedName("occupancy")
    @Expose
    private Integer occupancy;
    @SerializedName("income")
    @Expose
    private Integer income;
    @SerializedName("nights")
    @Expose
    private Integer nights;
    @SerializedName("max_income")
    @Expose
    private Integer maxIncome;
    @SerializedName("max_income_guest")
    @Expose
    private String maxIncomeGuest;
    @SerializedName("avg_income")
    @Expose
    private Integer avgIncome;
    @SerializedName("max_nights")
    @Expose
    private Integer maxNights;
    @SerializedName("max_nights_guest")
    @Expose
    private String maxNightsGuest;
    @SerializedName("avg_nights")
    @Expose
    private Integer avgNights;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("month")
    @Expose
    private Integer month;
    @SerializedName("year")
    @Expose
    private Integer year;

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
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

    public Integer getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(Integer maxIncome) {
        this.maxIncome = maxIncome;
    }

    public String getMaxIncomeGuest() {
        return maxIncomeGuest;
    }

    public void setMaxIncomeGuest(String maxIncomeGuest) {
        this.maxIncomeGuest = maxIncomeGuest;
    }

    public Integer getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Integer avgIncome) {
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

    public Integer getAvgNights() {
        return avgNights;
    }

    public void setAvgNights(Integer avgNights) {
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