package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pwd")
    @Expose
    private String pwd;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("access")
    @Expose
    private Access access;
    @SerializedName("properties")
    @Expose
    private String properties;
    @SerializedName("undo_timer")
    @Expose
    private String undoTimer;
    @SerializedName("notify_overbooking")
    @Expose
    private String notifyOverbooking;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getUndoTimer() {
        return undoTimer;
    }

    public void setUndoTimer(String undoTimer) {
        this.undoTimer = undoTimer;
    }

    public String getNotifyOverbooking() {
        return notifyOverbooking;
    }

    public void setNotifyOverbooking(String notifyOverbooking) {
        this.notifyOverbooking = notifyOverbooking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
