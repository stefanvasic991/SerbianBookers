package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GuestList {

    @SerializedName("key")
    private String key;
    @SerializedName("account")
    private String account;
    @SerializedName("lcode")
    private String lcode;
    @SerializedName("status")
    private String status;
    @SerializedName("guests")
    private ArrayList<Guest> guestList = new ArrayList<>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }
}
