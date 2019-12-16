package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowCard {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("rcode")
    @Expose
    private String rcode;
    @SerializedName("cc_pass")
    @Expose
    private String ccPass;


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ShowCardData showCardData;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getCcPass() {
        return ccPass;
    }

    public void setCcPass(String ccPass) {
        this.ccPass = ccPass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShowCardData getShowCardData() {
        return showCardData;
    }

    public void setShowCardData(ShowCardData showCardData) {
        this.showCardData = showCardData;
    }
}
