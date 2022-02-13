/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 HuyLQ           Update properties
 */
package model;

public class RoomStatus {

    private Room room;
    private int bedAvailable;
    private int status;

    /**
     * Initializes a newly created <code>RoomStatus</code> object so that it
     * represents an empty information <code>RoomStatus</code>.
     */
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
