package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsChannel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("income")
    @Expose
    private Integer income;
    @SerializedName("costs")
    @Expose
    private Integer costs;
    @SerializedName("earnings")
    @Expose
    private Integer earnings;
    @SerializedName("avg_income")
    @Expose
    private Integer avgIncome;
    @SerializedName("canceled")
    @Expose
    private Integer canceled;
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

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getCosts() {
        return costs;
    }

    public void setCosts(Integer costs) {
        this.costs = costs;
    }

    public Integer getEarnings() {
        return earnings;
    }

    public void setEarnings(Integer earnings) {
        this.earnings = earnings;
    }

    public Integer getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(Integer avgIncome) {
        this.avgIncome = avgIncome;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
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
