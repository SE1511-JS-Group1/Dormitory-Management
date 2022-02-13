/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DuyTN          Update properties
 */
package model;

public class Violation {

    private int violationID;
    private String type;
    private String violatorID;
    private String discription;
    private String penalization;

    /**
     * Initializes a newly created <code>Violation</code> object so that it
     * represents an empty information <code>Violation</code>.
     */
    public Violation() {
    }

    public Violation(int violationID, String type, String violatorID, String discription, String penalization) {
        this.violationID = violationID;
        this.type = type;
        this.violatorID = violatorID;
        this.discription = discription;
        this.penalization = penalization;
    }

    public int getViolationID() {
        return violationID;
    }

    public void setViolationID(int violationID) {
        this.violationID = violationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViolatorID() {
        return violatorID;
    }

    public void setViolatorID(String violatorID) {
        this.violatorID = violatorID;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPenalization() {
        return penalization;
    }

    public void setPenalization(String penalization) {
        this.penalization = penalization;
    }

}
