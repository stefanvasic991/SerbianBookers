package com.easyswitch.serbianbookers.models;

import java.util.List;

public class NewPrice {

    private String roomId;
    private List<Integer> availabilityData;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Integer> getAvailabilityData() {
        return availabilityData;
    }

    public void setAvailabilityData(List<Integer> availabilityData) {
        this.availabilityData = availabilityData;
    }
}
