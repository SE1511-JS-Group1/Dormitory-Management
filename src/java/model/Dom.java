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
public class Dom {

    private String domID;
    private String domName;

    public Dom() {
    }

    public Dom(String name, String position) {
        this.domID = name;
        this.domName = position;
    }

    public String getDomID() {
        return domID;
    }

    public void setDomID(String domID) {
        this.domID = domID;
    }

    public String getDomName() {
        return domName;
    }

    public void setDomName(String domName) {
        this.domName = domName;
    }

}
