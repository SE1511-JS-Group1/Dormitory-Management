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

/**
 * The class define <code>Management</code> object: <code>Management</code> of
 * dormitory system. Its properties base on Account entity properties in
 * database.
 *
 * @author DinhLX
 */
public class Management {

    private Dom dom;
    private DomManager domManager;

    /**
     * Initializes a newly created <code>Management</code> object so that it
     * represents an empty information <code>Management</code>.
     */
    public Management() {
    }

    /**
     * Initializes a newly created <code>Management</code> object with all
     * information of each <code>Management</code>.
     * <br> The <code>Management</code> information includes 2 attributes: dom,
     * domManager.
     * <br>
     *
     * @param dom<code>dom</code> object. includes all attributes of the
     * <code>Dom</code> class.
     * @param domManager<code>DomManager</code> object. includes all attributes
     * of the <code>DomManager</code> class.
     */
    public Management(Dom dom, DomManager domManager) {
        this.dom = dom;
        this.domManager = domManager;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public DomManager getDomManager() {
        return domManager;
    }

    public void setDomManager(DomManager domManager) {
        this.domManager = domManager;
    }

}
