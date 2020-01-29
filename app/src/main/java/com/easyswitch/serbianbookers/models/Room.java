package com.easyswitch.serbianbookers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortname")
    @Expose
    private String shortname;
    @SerializedName("occupancy")
    @Expose
    private String occupancy;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("boards")
    @Expose
    private Object boards;
    @SerializedName("rtype")
    @Expose
    private String rtype;
    @SerializedName("rtype_name")
    @Expose
    private String rtypeName;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("subrooms")
    @Expose
    private String subrooms;
    @SerializedName("master_room")
    @Expose
    private String masterRoom;
    @SerializedName("parent_room")
    @Expose
    private String parentRoom;
    @SerializedName("room_numbers")
    @Expose
    private String roomNumbers;

    private boolean selected;


    protected Room(Parcel in) {
        id = in.readString();
        name = in.readString();
        shortname = in.readString();
        occupancy = in.readString();
        price = in.readString();
        availability = in.readString();
        board = in.readString();
        rtype = in.readString();
        rtypeName = in.readString();
        color = in.readString();
        subrooms = in.readString();
        masterRoom = in.readString();
        parentRoom = in.readString();
        roomNumbers = in.readString();
        selected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(shortname);
        dest.writeString(occupancy);
        dest.writeString(price);
        dest.writeString(availability);
        dest.writeString(board);
        dest.writeString(rtype);
        dest.writeString(rtypeName);
        dest.writeString(color);
        dest.writeString(subrooms);
        dest.writeString(masterRoom);
        dest.writeString(parentRoom);
        dest.writeString(roomNumbers);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
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

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Object getBoards() {
        return boards;
    }

    public void setBoards(Object boards) {
        this.boards = boards;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRtypeName() {
        return rtypeName;
    }

    public void setRtypeName(String rtypeName) {
        this.rtypeName = rtypeName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSubrooms() {
        return subrooms;
    }

    public void setSubrooms(String subrooms) {
        this.subrooms = subrooms;
    }

    public String getMasterRoom() {
        return masterRoom;
    }

    public void setMasterRoom(String masterRoom) {
        this.masterRoom = masterRoom;
    }

    public String getParentRoom() {
        return parentRoom;
    }

    public void setParentRoom(String parentRoom) {
        this.parentRoom = parentRoom;
    }

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
