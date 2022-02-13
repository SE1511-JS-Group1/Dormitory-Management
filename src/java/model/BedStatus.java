/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DucHT           Update properties
 */
package model;

/**
 * The class define <code>BedStatus</code> object: state information of
 * <code>Bed</code>. Its properties base on BedStatus entity properties in
 * database.
 *
 * @author DucHT
 */
public class BedStatus {

    private Room room;
    private int bedNo;
    private boolean status;

    /**
     * Initializes a newly created <code>BedStatus</code> object so that it
     * represents an empty information <code>BedStatus</code>.
     */
    public BedStatus() {
    }

    /**
     * Initializes a newly created <code>BedStatus</code> object with all
     * information of each <code>BedStatus</code>.
     * <br> The <code>BedStatus</code> information includes 3 attributes: room,
     * bedNo, status.
     *
     * @param room <code>Room</code> class. <code>room</code> it includes
     * information of a room including: roomID, domroomName, floor, category.
     * @param bedNo <code>int</code> Bed IDs are entered in order by the school
     * @param status <code>int</code> bed status .If <code>true</code> means
     * someone has already registered, <code>false</code> means no one has
     * registered and can register.
     *
     *
     */
    public BedStatus(Room room, int bedNo, boolean status) {
        this.room = room;
        this.bedNo = bedNo;
        this.status = status;
    }

    /**
     * Allow user to get the room of this <code>Room</code> object.
     * <br>
     * result contains a <code>Room</code> class. Displays a <code>room</code>
     * object. includes all information of a class Room
     *
     * @return a <code>Room</code> class.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Allow user to set up the room of this <code>Room</code> object.
     * <br>
     *
     * @param room<code>int</code> object. includes all information of a class
     * Room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
