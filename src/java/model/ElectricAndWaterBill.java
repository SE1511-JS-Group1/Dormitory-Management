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
public class ElectricAndWaterBill {

    private int billID;
    private Room room;
    private String month;
    private Date deadline;
    private double waterAmount;
    private double eletricAmount;
    private String status;

    public ElectricAndWaterBill() {
    }

    public ElectricAndWaterBill(int billID, Room room, String month, Date deadline, double waterAmount, double eletricAmount, String status) {
        this.billID = billID;
        this.room = room;
        this.month = month;
        this.deadline = deadline;
        this.waterAmount = waterAmount;
        this.eletricAmount = eletricAmount;
        this.status = status;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public double getEletricAmount() {
        return eletricAmount;
    }

    public void setEletricAmount(double eletricAmount) {
        this.eletricAmount = eletricAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
