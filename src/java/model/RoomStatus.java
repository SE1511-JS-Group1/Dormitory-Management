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
public class RoomStatus {

    private Room room;
    private int bedAvailable;
    private int status;

    public RoomStatus() {
    }

    public RoomStatus(Room room, int bedAvailable, int status) {
        this.room = room;
        this.bedAvailable = bedAvailable;
        this.status = status;
    }

    public int getBedAvailable() {
        return bedAvailable;
    }

    public void setBedAvailable(int bedAvailable) {
        this.bedAvailable = bedAvailable;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
