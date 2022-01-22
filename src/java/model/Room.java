/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lenovo_thinkpad
 */
public class Room {

    private int roomID;
    private Dom dom;
    private String roomName;
    private int floor;
    private RoomCategory category;

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

}
