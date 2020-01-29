package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewValuesRestriction {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("days")
    @Expose
    private List<Restriction> days = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Restriction> getDays() {
        return days;
    }

    public void setDays(List<Restriction> days) {
        this.days = days;
    }
}
