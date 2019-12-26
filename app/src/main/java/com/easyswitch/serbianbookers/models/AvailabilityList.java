package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailabilityList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("data")
    @Expose
    private List<AvailabilityData> data = null;
    @SerializedName("shortname")
    @Expose
    private String shortName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AvailabilityData> getData() {
        return data;
    }

    public void setData(List<AvailabilityData> data) {
        this.data = data;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
