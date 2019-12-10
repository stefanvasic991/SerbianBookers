package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availability {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("avail")
    @Expose
    private Avail avail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Avail getAvail() {
        return avail;
    }

    public void setAvail(Avail avail) {
        this.avail = avail;
    }
}
