/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DinhLX           Update properties
 */
package model;

import java.sql.Date;

/**
 * The class define <code>ElectricAndWaterBill</code> object:
 * <code>Room</code>'s utility bill Its properties base on
 * ElectricAndWaterBillproperties in database.
 *
 * @author DinhLX
 */
public class ElectricAndWaterBill {

    private int billID;
    private Room room;
    private String month;
    private Date deadline;
    private double waterAmount;
    private double eletricAmount;
    private String status;

    /**
     * Initializes a newly created <code>ElectricAndWaterBill</code> object so
     * that it represents an empty information
     * <code>ElectricAndWaterBill</code>.
     */
    public ElectricAndWaterBill() {
    }

    /**
     * Initializes a newly created <code>ElectricAndWaterBill</code> object with
     * all information of each <code>ElectricAndWaterBill</code>.
     * <br> The <code>ElectricAndWaterBill</code> information includes 7
     * attributes: billID, room, month, deadline, waterAmount, eletricAmount and
     * status.
     *
     * @param billID<code>int</code> object. billID provided by the school.
     * @param room <code>Room</code> class. <code>room</code> it includes
     * information of a room including: roomID, domroomName, floor, category.
     * @param month<code>java.sql.Date</code> object. applicable month of
     * <code>ElectricAndWaterBill</code>'s
     * @param deadline<code>java.sql.Date</code> object. expiration date of
     * <code>ElectricAndWaterBill</code>'s
     * @param waterAmount<code>int</code> object. the number of countries
     * <code>Boarder</code> used in the last month
     * @param eletricAmount <code>int/code> object. the number that
     * <code>Boarder</code> used in the last month
     * @param status<code>int</code> object. if
     * <code>true</code> then <code>ElectricAndWaterBill</code> has been paid
     * <code>false</code> unpaid
     *
     */
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
