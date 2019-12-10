package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayPrice {

    @SerializedName("288968")
    @Expose
    private List<Double> _288968 = null;

    public List<Double> get288968() {
        return _288968;
    }

    public void set288968(List<Double> _288968) {
        this._288968 = _288968;
    }
}
