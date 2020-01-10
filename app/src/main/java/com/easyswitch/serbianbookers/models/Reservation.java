package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Reservation implements Parcelable {

    @SerializedName("reservation_code")
    @Expose
    private String reservationCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("channel_reservation_code")
    @Expose
    private String channelReservationCode;
    @SerializedName("id_woodoo")
    @Expose
    private String idWoodoo;
    @SerializedName("fount")
    @Expose
    private String fount;
    @SerializedName("modified_reservations")
    @Expose
    private String modifiedReservations;
    @SerializedName("was_modified")
    @Expose
    private String wasModified;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("date_received")
    @Expose
    private String dateReceived;
    @SerializedName("time_received")
    @Expose
    private String timeReceived;
    @SerializedName("date_arrival")
    @Expose
    private String dateArrival;
    @SerializedName("date_departure")
    @Expose
    private String dateDeparture;
    @SerializedName("nights")
    @Expose
    private String nights;
    @SerializedName("boards")
    @Expose
    private Object boards;
    @SerializedName("men")
    @Expose
    private String men;
    @SerializedName("children")
    @Expose
    private String children;
    @SerializedName("payment_gateway_fee")
    @Expose
    private String paymentGatewayFee;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("customer_city")
    @Expose
    private String customerCity;
    @SerializedName("customer_country")
    @Expose
    private String customerCountry;
    @SerializedName("customer_mail")
    @Expose
    private String customerMail;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_surname")
    @Expose
    private String customerSurname;
    @SerializedName("customer_notes")
    @Expose
    private String customerNotes;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_language")
    @Expose
    private String customerLanguage;
    @SerializedName("customer_zip")
    @Expose
    private String customerZip;
    @SerializedName("rooms")
    @Expose
    private String rooms;
    @SerializedName("room_numbers")
    @Expose
    private String roomNumbers;
    @SerializedName("room_opportunities")
    @Expose
    private String roomOpportunities;
    @SerializedName("opportunities")
    @Expose
    private String opportunities;
    @SerializedName("dayprices")
    @Expose
    private DayPrice dayprices;
    @SerializedName("special_offer")
    @Expose
    private String specialOffer;
    @SerializedName("addons_list")
    @Expose
    private String addonsList;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("deleted_advance")
    @Expose
    private String deletedAdvance;
    @SerializedName("services")
    @Expose
    private List<Object> services = null;
    @SerializedName("services_price")
    @Expose
    private String servicesPrice;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("invoices")
    @Expose
    private String invoices;
    @SerializedName("cc_info")
    @Expose
    private String ccInfo;
    @SerializedName("channel_logo")
    @Expose
    private String channelLogo;


    protected Reservation(Parcel in) {
        reservationCode = in.readString();
        status = in.readString();
        channelReservationCode = in.readString();
        idWoodoo = in.readString();
        fount = in.readString();
        modifiedReservations = in.readString();
        wasModified = in.readString();
        amount = in.readString();
        dateReceived = in.readString();
        timeReceived = in.readString();
        dateArrival = in.readString();
        dateDeparture = in.readString();
        nights = in.readString();
        men = in.readString();
        children = in.readString();
        paymentGatewayFee = in.readString();
        customerId = in.readString();
        customerCity = in.readString();
        customerCountry = in.readString();
        customerMail = in.readString();
        customerName = in.readString();
        customerSurname = in.readString();
        customerNotes = in.readString();
        customerPhone = in.readString();
        customerAddress = in.readString();
        customerLanguage = in.readString();
        customerZip = in.readString();
        rooms = in.readString();
        roomNumbers = in.readString();
        roomOpportunities = in.readString();
        opportunities = in.readString();
        specialOffer = in.readString();
        addonsList = in.readString();
        deletedAdvance = in.readString();
        servicesPrice = in.readString();
        totalPrice = in.readString();
        createdBy = in.readString();
        invoices = in.readString();
        ccInfo = in.readString();
        channelLogo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reservationCode);
        dest.writeString(status);
        dest.writeString(channelReservationCode);
        dest.writeString(idWoodoo);
        dest.writeString(fount);
        dest.writeString(modifiedReservations);
        dest.writeString(wasModified);
        dest.writeString(amount);
        dest.writeString(dateReceived);
        dest.writeString(timeReceived);
        dest.writeString(dateArrival);
        dest.writeString(dateDeparture);
        dest.writeString(nights);
        dest.writeString(men);
        dest.writeString(children);
        dest.writeString(paymentGatewayFee);
        dest.writeString(customerId);
        dest.writeString(customerCity);
        dest.writeString(customerCountry);
        dest.writeString(customerMail);
        dest.writeString(customerName);
        dest.writeString(customerSurname);
        dest.writeString(customerNotes);
        dest.writeString(customerPhone);
        dest.writeString(customerAddress);
        dest.writeString(customerLanguage);
        dest.writeString(customerZip);
        dest.writeString(rooms);
        dest.writeString(roomNumbers);
        dest.writeString(roomOpportunities);
        dest.writeString(opportunities);
        dest.writeString(specialOffer);
        dest.writeString(addonsList);
        dest.writeString(deletedAdvance);
        dest.writeString(servicesPrice);
        dest.writeString(totalPrice);
        dest.writeString(createdBy);
        dest.writeString(invoices);
        dest.writeString(ccInfo);
        dest.writeString(channelLogo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannelReservationCode() {
        return channelReservationCode;
    }

    public void setChannelReservationCode(String channelReservationCode) {
        this.channelReservationCode = channelReservationCode;
    }

    public String getIdWoodoo() {
        return idWoodoo;
    }

    public void setIdWoodoo(String idWoodoo) {
        this.idWoodoo = idWoodoo;
    }

    public String getFount() {
        return fount;
    }

    public void setFount(String fount) {
        this.fount = fount;
    }

    public String getModifiedReservations() {
        return modifiedReservations;
    }

    public void setModifiedReservations(String modifiedReservations) {
        this.modifiedReservations = modifiedReservations;
    }

    public String getWasModified() {
        return wasModified;
    }

    public void setWasModified(String wasModified) {
        this.wasModified = wasModified;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public Object getBoards() {
        return boards;
    }

    public void setBoards(Object boards) {
        this.boards = boards;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getPaymentGatewayFee() {
        return paymentGatewayFee;
    }

    public void setPaymentGatewayFee(String paymentGatewayFee) {
        this.paymentGatewayFee = paymentGatewayFee;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerLanguage() {
        return customerLanguage;
    }

    public void setCustomerLanguage(String customerLanguage) {
        this.customerLanguage = customerLanguage;
    }

    public String getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public String getRoomOpportunities() {
        return roomOpportunities;
    }

    public void setRoomOpportunities(String roomOpportunities) {
        this.roomOpportunities = roomOpportunities;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public DayPrice getDayprices() {
        return dayprices;
    }

    public void setDayprices(DayPrice dayprices) {
        this.dayprices = dayprices;
    }

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
    }

    public String getAddonsList() {
        return addonsList;
    }

    public void setAddonsList(String addonsList) {
        this.addonsList = addonsList;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getDeletedAdvance() {
        return deletedAdvance;
    }

    public void setDeletedAdvance(String deletedAdvance) {
        this.deletedAdvance = deletedAdvance;
    }

    public List<Object> getServices() {
        return services;
    }

    public void setServices(List<Object> services) {
        this.services = services;
    }

    public String getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(String servicesPrice) {
        this.servicesPrice = servicesPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getInvoices() {
        return invoices;
    }

    public void setInvoices(String invoices) {
        this.invoices = invoices;
    }

    public String getCcInfo() {
        return ccInfo;
    }

    public void setCcInfo(String ccInfo) {
        this.ccInfo = ccInfo;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationCode='" + reservationCode + '\'' +
                ", status='" + status + '\'' +
                ", channelReservationCode='" + channelReservationCode + '\'' +
                ", idWoodoo='" + idWoodoo + '\'' +
                ", fount='" + fount + '\'' +
                ", modifiedReservations='" + modifiedReservations + '\'' +
                ", wasModified='" + wasModified + '\'' +
                ", amount='" + amount + '\'' +
                ", dateReceived='" + dateReceived + '\'' +
                ", timeReceived='" + timeReceived + '\'' +
                ", dateArrival='" + dateArrival + '\'' +
                ", dateDeparture='" + dateDeparture + '\'' +
                ", nights='" + nights + '\'' +
                ", boards='" + boards + '\'' +
                ", men='" + men + '\'' +
                ", children='" + children + '\'' +
                ", paymentGatewayFee='" + paymentGatewayFee + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerCountry='" + customerCountry + '\'' +
                ", customerMail='" + customerMail + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", customerNotes='" + customerNotes + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerLanguage='" + customerLanguage + '\'' +
                ", customerZip='" + customerZip + '\'' +
                ", rooms='" + rooms + '\'' +
                ", roomNumbers='" + roomNumbers + '\'' +
                ", roomOpportunities='" + roomOpportunities + '\'' +
                ", opportunities='" + opportunities + '\'' +
                ", dayprices=" + dayprices +
                ", specialOffer='" + specialOffer + '\'' +
                ", addonsList='" + addonsList + '\'' +
                ", discount=" + discount +
                ", deletedAdvance='" + deletedAdvance + '\'' +
                ", services=" + services +
                ", servicesPrice='" + servicesPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", invoices='" + invoices + '\'' +
                ", ccInfo='" + ccInfo + '\'' +
                '}';
    }

    private class Board {
    }
}
