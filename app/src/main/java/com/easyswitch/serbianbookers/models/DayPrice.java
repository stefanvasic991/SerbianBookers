package com.easyswitch.serbianbookers.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayPrice {

    @SerializedName("288968")
    @Expose
    private List<Double> dayPrice = null;

    public List<Double> getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(List<Double> dayPrice) {
        this.dayPrice = dayPrice;
    }
}