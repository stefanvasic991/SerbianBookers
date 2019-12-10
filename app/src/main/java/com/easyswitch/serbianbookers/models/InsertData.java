package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertData {

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
    private Object oldData;
    @SerializedName("new_data")
    @Expose
    private NewData newData;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_time")
    @Expose
    private String createdTime;

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

    public Object getOldData() {
        return oldData;
    }

    public void setOldData(Object oldData) {
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
