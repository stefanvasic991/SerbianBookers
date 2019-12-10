package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewData implements Parcelable {

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

    protected NewData(Parcel in) {
        username = in.readString();
        email = in.readString();
        pwd = in.readString();
        name = in.readString();
        account = in.readString();
        access = in.readParcelable(Access.class.getClassLoader());
        properties = in.readString();
        undoTimer = in.readString();
        notifyOverbooking = in.readString();
        status = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(pwd);
        dest.writeString(name);
        dest.writeString(account);
        dest.writeParcelable(access, flags);
        dest.writeString(properties);
        dest.writeString(undoTimer);
        dest.writeString(notifyOverbooking);
        dest.writeString(status);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewData> CREATOR = new Creator<NewData>() {
        @Override
        public NewData createFromParcel(Parcel in) {
            return new NewData(in);
        }

        @Override
        public NewData[] newArray(int size) {
            return new NewData[size];
        }
    };

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
