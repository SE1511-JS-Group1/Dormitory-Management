/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 AnhNNV           Update properties
 */
package model;

import java.util.Objects;

public class Room {

    private int roomID;
    private Dom dom;
    private String roomName;
    private int floor;
    private RoomCategory category;

    /**
     * Initializes a newly created <code>Room</code> object so that it
     * represents an empty information <code>Room</code>.
     */
    public Room() {
    }

    public Room(int roomID, Dom dom, String roomName, int floor, RoomCategory category) {
        this.roomID = roomID;
        this.dom = dom;
        this.roomName = roomName;
        this.floor = floor;
        this.category = category;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getDom().getDomID() + getRoomName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room another = (Room) obj;
            if (this.roomID == another.getRoomID()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.roomID;
        hash = 97 * hash + Objects.hashCode(this.dom);
        hash = 97 * hash + Objects.hashCode(this.roomName);
        hash = 97 * hash + this.floor;
        hash = 97 * hash + Objects.hashCode(this.category);
        return hash;
    }

}
