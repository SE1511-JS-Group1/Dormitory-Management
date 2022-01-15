/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lenovo_thinkpad
 */
public class Violation {

    private int violationID;
    private String type;
    private String violatorID;
    private String discription;
    private String penalization;

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
