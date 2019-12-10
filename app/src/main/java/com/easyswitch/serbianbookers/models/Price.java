package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("variation")
    @Expose
    private String variation;
    @SerializedName("variation_type")
    @Expose
    private String variationType;
    @SerializedName("vpid")
    @Expose
    private String vpid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getVariationType() {
        return variationType;
    }

    public void setVariationType(String variationType) {
        this.variationType = variationType;
    }

    public String getVpid() {
        return vpid;
    }

    public void setVpid(String vpid) {
        this.vpid = vpid;
    }

}