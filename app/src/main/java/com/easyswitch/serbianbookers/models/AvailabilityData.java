package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailabilityData {

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

    private boolean isChecked = false;

    public AvailabilityData() {

    }

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

