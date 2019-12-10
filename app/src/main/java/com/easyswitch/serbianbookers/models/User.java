package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("remember")
    @Expose
    private String remember;

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("access")
    @Expose
    private Access access;
    @SerializedName("theme")
    @Expose
    private String theme;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("undo_timer")
    @Expose
    private String undoTimer;
    @SerializedName("default_price")
    @Expose
    private String defaultPrice;
    @SerializedName("default_restriction")
    @Expose
    private String defaultRestriction;
    @SerializedName("notify_overbooking")
    @Expose
    private String notifyOverbooking;
    @SerializedName("properties")
    @Expose
    private ArrayList<Property> properties = null;

    public User(Parcel in) {
        status = in.readString();
        key = in.readString();
        username = in.readString();
        password = in.readString();
        remember = in.readString();
        account = in.readString();
        access = in.readParcelable(Access.class.getClassLoader());
        theme = in.readString();
        email = in.readString();
        undoTimer = in.readString();
        defaultPrice = in.readString();
        defaultRestriction = in.readString();
        notifyOverbooking = in.readString();
        properties = in.createTypedArrayList(Property.CREATOR);
    }

    public User() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(key);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(remember);
        dest.writeString(account);
        dest.writeParcelable(access, flags);
        dest.writeString(theme);
        dest.writeString(email);
        dest.writeString(undoTimer);
        dest.writeString(defaultPrice);
        dest.writeString(defaultRestriction);
        dest.writeString(notifyOverbooking);
        dest.writeTypedList(properties);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUndoTimer() {
        return undoTimer;
    }

    public void setUndoTimer(String undoTimer) {
        this.undoTimer = undoTimer;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getDefaultRestriction() {
        return defaultRestriction;
    }

    public void setDefaultRestriction(String defaultRestriction) {
        this.defaultRestriction = defaultRestriction;
    }

    public String getNotifyOverbooking() {
        return notifyOverbooking;
    }

    public void setNotifyOverbooking(String notifyOverbooking) {
        this.notifyOverbooking = notifyOverbooking;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public String toString() {
        return  "User [ username=" + username
                + ", password=" + password
                + "remember=" + remember +
                "]";
    }
}
