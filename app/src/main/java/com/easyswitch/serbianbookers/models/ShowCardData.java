package com.easyswitch.serbianbookers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowCardData {

    @SerializedName("cc_number")
    @Expose
    private String ccNumber;
    @SerializedName("cc_cvv")
    @Expose
    private String ccCvv;
    @SerializedName("cc_owner")
    @Expose
    private String ccOwner;
    @SerializedName("cc_expiring")
    @Expose
    private String ccExpiring;
}
