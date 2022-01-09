/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author lenovo_thinkpad
 */
public class Stay {

    private Room room;
    private Boarder boarder;
    private int bedNo;
    private Date startDate;
    private Date endDate;

    public Stay() {
    }

    public Stay(Room room, Boarder boarder, int bedNo, Date startDate, Date endDate) {
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
