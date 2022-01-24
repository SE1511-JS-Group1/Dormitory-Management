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
public class RoomCategory {

    private int categoryID;
    private String categoryName;
    private boolean roomGender;
    private double roomFee;
    private int bedNumber;

    public RoomCategory() {
    }

    public RoomCategory(int categoryID, String categoryName, boolean roomGender, double roomFee, int bedNumber) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.roomGender = roomGender;
        this.roomFee = roomFee;
        this.bedNumber = bedNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isRoomGender() {
        return roomGender;
    }

    public void setRoomGender(boolean roomGender) {
        this.roomGender = roomGender;
    }

    public double getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(double roomFee) {
        this.roomFee = roomFee;
    }

}
