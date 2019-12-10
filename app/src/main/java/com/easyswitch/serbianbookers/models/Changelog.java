package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Changelog implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("data_type")
    @Expose
    private String dataType;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("old_data")
    @Expose
    private NewData oldData;
    @SerializedName("new_data")
    @Expose
    private NewData newData;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_time")
    @Expose
    private String createdTime;

    protected Changelog(Parcel in) {
        id = in.readString();
        dataType = in.readString();
        action = in.readString();
        newData = in.readParcelable(NewData.class.getClassLoader());
        createdBy = in.readString();
        createdTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dataType);
        dest.writeString(action);
        dest.writeParcelable(newData, flags);
        dest.writeString(createdBy);
        dest.writeString(createdTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Changelog> CREATOR = new Creator<Changelog>() {
        @Override
        public Changelog createFromParcel(Parcel in) {
            return new Changelog(in);
        }

        @Override
        public Changelog[] newArray(int size) {
            return new Changelog[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public NewData getOldData() {
        return oldData;
    }

    public void setOldData(NewData oldData) {
        this.oldData = oldData;
    }

    public NewData getNewData() {
        return newData;
    }

    public void setNewData(NewData newData) {
        this.newData = newData;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
