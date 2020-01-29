package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertRestriction {


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
    @SerializedName("log")
    @Expose
    private String log;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("old_values")
    @Expose
    private List<NewValuesRestriction> oldValues;
    @SerializedName("new_values")
    @Expose
    private List<NewValuesRestriction> newValues;
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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<NewValuesRestriction> getOldValues() {
        return oldValues;
    }

    public void setOldValues(List<NewValuesRestriction> oldValues) {
        this.oldValues = oldValues;
    }

    public List<NewValuesRestriction> getNewValues() {
        return newValues;
    }

    public void setNewValues(List<NewValuesRestriction> newValues) {
        this.newValues = newValues;
    }

    public List<String> getMultipleIDs() {
        return multipleIDs;
    }

    public void setMultipleIDs(List<String> multipleIDs) {
        this.multipleIDs = multipleIDs;
    }
}
