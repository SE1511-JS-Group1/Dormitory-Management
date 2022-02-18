/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DUCHT          Update properties
 */
package model;

/**
 * The class define <code>Dom</code> object: all <code>Dom</code>. Boarders can
 * be accessed to register for accommodation Its properties base on Dom entity
 * properties in database.
 *
 * @author DUCHT
 */
public class Dom {

    private String domID;
    private String domName;

    /**
     * Initializes a newly created <code>Dom</code> object so that it represents
     * an empty information <code>Dom</code>.
     */
    public Dom() {
    }

    /**
     * Initializes a newly created <code>Dom</code> object with all information
     * of each <code>Dom</code>.
     * <br> The <code>Dom</code> information include 2 attributes: domID,
     * domName.
     * <br>
     *
     * @param domID<code>int</code> object <code>domID</code>in Dom ID provided
     * by the school.
     * @param domName<code>java.lang.String</code> object. <code>Dom</code>'s
     * name
     */
    public Dom(String domID, String domName) {
        this.domID = domID;
        this.domName = domName;
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
