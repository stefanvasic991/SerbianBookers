package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guest implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("host_again")
    @Expose
    private String hostAgain;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("total_arrivals")
    @Expose
    private String totalArrivals;
    @SerializedName("total_nights")
    @Expose
    private String totalNights;
    @SerializedName("total_paid")
    @Expose
    private String totalPaid;
    @SerializedName("documents")
    @Expose
    private String documents;
    @SerializedName("created_by")
    @Expose
    private String createdBy;

    protected Guest(Parcel in) {
        id = in.readString();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        phone = in.readString();
        country = in.readString();
        dateOfBirth = in.readString();
        gender = in.readString();
        hostAgain = in.readString();
        note = in.readString();
        totalArrivals = in.readString();
        totalNights = in.readString();
        totalPaid = in.readString();
        documents = in.readString();
        createdBy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(country);
        dest.writeString(dateOfBirth);
        dest.writeString(gender);
        dest.writeString(hostAgain);
        dest.writeString(note);
        dest.writeString(totalArrivals);
        dest.writeString(totalNights);
        dest.writeString(totalPaid);
        dest.writeString(documents);
        dest.writeString(createdBy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Guest> CREATOR = new Creator<Guest>() {
        @Override
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        @Override
        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHostAgain() {
        return hostAgain;
    }

    public void setHostAgain(String hostAgain) {
        this.hostAgain = hostAgain;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotalArrivals() {
        return totalArrivals;
    }

    public void setTotalArrivals(String totalArrivals) {
        this.totalArrivals = totalArrivals;
    }

    public String getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(String totalNights) {
        this.totalNights = totalNights;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
