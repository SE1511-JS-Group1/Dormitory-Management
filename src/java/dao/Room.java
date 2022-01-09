/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lenovo_thinkpad
 */
public class Room {

    private String roomID;
    private int floor;
    private int categoryID;

    public Room() {
    }

    public Room(String roomID, int floor, int categoryID) {
        this.roomID = roomID;
        this.floor = floor;
        this.categoryID = categoryID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

}
