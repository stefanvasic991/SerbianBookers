package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsChannel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("income")
    @Expose
    private Double income;
    @SerializedName("costs")
    @Expose
    private Double costs;
    @SerializedName("earnings")
    @Expose
    private Double earnings;
    @SerializedName("avg_income")
    @Expose
    private Double avgIncome;
    @SerializedName("canceled")
    @Expose
    private Double canceled;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("id")
    @Expose
    private String id;

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

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public Double getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Double avgIncome) {
        this.avgIncome = avgIncome;
    }

    public Double getCanceled() {
        return canceled;
    }

    public void setCanceled(Double canceled) {
        this.canceled = canceled;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
