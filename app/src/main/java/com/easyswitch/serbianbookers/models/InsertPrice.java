package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertPrice {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("lcode")
    @Expose
    private String lcode;
    @SerializedName("dfrom")
    @Expose
    private String dfrom;
    @SerializedName("dto")
    @Expose
    private String dto;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("old_values")
    @Expose
    private List<Values> oldValues;
    @SerializedName("new_values")
    @Expose
    private List<Values> newValues;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private InsertData insertData;
    @SerializedName("multiple_ids")
    @Expose
    List<String> multipleIDs;

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

    public String getDfrom() {
        return dfrom;
    }

    public void setDfrom(String dfrom) {
        this.dfrom = dfrom;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Values> getOldValues() {
        return oldValues;
    }

    public void setOldValues(List<Values> oldValues) {
        this.oldValues = oldValues;
    }

    public List<Values> getNewValues() {
        return newValues;
    }

    public void setNewValues(List<Values> newValues) {
        this.newValues = newValues;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InsertData getInsertData() {
        return insertData;
    }

    public void setInsertData(InsertData insertData) {
        this.insertData = insertData;
    }

    public List<String> getMultipleIDs() {
        return multipleIDs;
    }

    public void setMultipleIDs(List<String> multipleIDs) {
        this.multipleIDs = multipleIDs;
    }
}
