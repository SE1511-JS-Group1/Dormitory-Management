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

import java.sql.Date;

/**
 * The class define <code>BoardingInformation</code> object: address information
 * of <code>Boarder</code>. bed registered successfully Its properties base on
 * BoardingInformation entity properties in database.
 *
 * @author DucHT
 */
public class BoardingInformation {

    private Room room;
    private Boarder boarder;
    private int bedNo;
    private Date startDate;
    private Date endDate;

    /**
     * Initializes a newly created <code>BoardingInformation</code> object so
     * that it represents an empty information <code>BoardingInformation</code>.
     */
    public BoardingInformation() {
    }

    /**
     * Initializes a newly created <code>Boarder</code> object with all
     * information of each <code>Boarder</code>.
     * <br> The <code>Boarder</code> information include 7 attributes: roll
     * number, name, date of birth, gender, email, phone number and job.
     *
     * @param room <code>Room</code> class. <code>room</code> it includes
     * information of a room including: roomID, domroomName, floor, category.
     * @param boarder <code>Boarder</code> class.it includes information of a
     * room including: rollNumber, boarderName, dateOfBirth, gender, email,
     * phoneNumber, job, account.
     * @param bedNo <code>int</code> object. <code>bedNo</code> provided by the
     * school.
     * @param startDate <code>java.sql.Date</code> object. startDate
     * <code>Boarder</code> successfully registered <code>bedNo</code>
     * @param endDate <code>java.sql.Date</code> object. endDate of
     * <code>bedNo</code> to which <code>Boarder</code> has registered
     */
    public BoardingInformation(Room room, Boarder boarder, int bedNo, Date startDate, Date endDate) {
        this.room = room;
        this.boarder = boarder;
        this.bedNo = bedNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Boarder getBoarder() {
        return boarder;
    }

    public void setBoarder(Boarder boarder) {
        this.boarder = boarder;
    }

}
