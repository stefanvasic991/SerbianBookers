package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class News {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("news_order_by")
    @Expose
    private String newsOrderBy;
    @SerializedName("news_order_type")
    @Expose
    private String newsOrderType;
    @SerializedName("news_dfrom")
    @Expose
    private String newsDfrom;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("received")
    @Expose
    private List<Reservation> received = null;
    @SerializedName("modified")
    @Expose
    private ArrayList<Object> modified = null;
    @SerializedName("canceled")
    @Expose
    private ArrayList<Reservation> canceled = null;

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

    public String getNewsOrderBy() {
        return newsOrderBy;
    }

    public void setNewsOrderBy(String newsOrderBy) {
        this.newsOrderBy = newsOrderBy;
    }

    public String getNewsOrderType() {
        return newsOrderType;
    }

    public void setNewsOrderType(String newsOrderType) {
        this.newsOrderType = newsOrderType;
    }

    public String getNewsDfrom() {
        return newsDfrom;
    }

    public void setNewsDfrom(String newsDfrom) {
        this.newsDfrom = newsDfrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getReceived() {
        return received;
    }

    public void setReceived(List<Reservation> received) {
        this.received = received;
    }

    public ArrayList<Object> getModified() {
        return modified;
    }

    public void setModified(ArrayList<Object> modified) {
        this.modified = modified;
    }

    public ArrayList<Reservation> getCanceled() {
        return canceled;
    }

    public void setCanceled(ArrayList<Reservation> canceled) {
        this.canceled = canceled;
    }
}
