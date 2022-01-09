/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomFeeBill {

    private int billID;
    private Boarder boarder;
    private String month;
    private Date deadline;
    private String status;

    public RoomFeeBill() {
    }

    public RoomFeeBill(int billID, Boarder boarder, String month, Date deadline, String status) {
        this.billID = billID;
        this.boarder = boarder;
        this.month = month;
        this.deadline = deadline;
        this.status = status;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Boarder getBoarder() {
        return boarder;
    }

    public void setBoarder(Boarder boarder) {
        this.boarder = boarder;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
