/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 AnhNNV           Update properties
 */
package model;

public class RoomCategory {

    private int categoryID;
    private String categoryName;
    private boolean roomGender;
    private double roomFee;
    private int bedNumber;

    /**
     * Initializes a newly created <code>RoomCategory</code> object so that it
     * represents an empty information <code>RoomCategory</code>.
     */
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
