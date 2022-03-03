/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-02-23      1.0                 DucHT           First Implement
 */
package model;

import java.util.ArrayList;

/**
 * This class define <code>DomInformation</code> object. It contains information
 * of each dom in the system. About <code>DomManager</code> of this
 * <code>Dom</code> and total, status of <code>Room</code>, Bed, in this dom.
 *
 * @author DucHT
 */
public class DomInformation {

    private Dom dom;
    private ArrayList<DomManager> domManagers;
    private int totalRoom;
    private int totalBed;
    private int bookedBed;

    public DomInformation() {
    }

    public int getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(int totalRoom) {
        this.totalRoom = totalRoom;
    }

    public DomInformation(Dom dom, ArrayList<DomManager> domManagers, int totalRoom, int totalBed, int bookedBed) {
        this.dom = dom;
        this.domManagers = domManagers;
        this.totalRoom = totalRoom;
        this.totalBed = totalBed;
        this.bookedBed = bookedBed;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public ArrayList<DomManager> getDomManagers() {
        return domManagers;
    }

    public void setDomManagers(ArrayList<DomManager> domManagers) {
        this.domManagers = domManagers;
    }

    public int getTotalBed() {
        return totalBed;
    }

    public void setTotalBed(int totalBed) {
        this.totalBed = totalBed;
    }

    public int getBookedBed() {
        return bookedBed;
    }

    public void setBookedBed(int bookedBed) {
        this.bookedBed = bookedBed;
    }

}
