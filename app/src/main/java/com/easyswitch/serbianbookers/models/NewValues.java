package com.easyswitch.serbianbookers.models;

import java.util.List;

public class NewValues {

    private String roomId;
    private List<InsertAvailData> availabilityData;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<InsertAvailData> getAvailabilityData() {
        return availabilityData;
    }

    public void setAvailabilityData(List<InsertAvailData> availabilityData) {
        this.availabilityData = availabilityData;
    }
}
