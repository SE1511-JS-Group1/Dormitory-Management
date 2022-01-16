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
public class Notice {
    private int id;
    private Date timeSend;
    private String title;
    private Boarder boarder;
    private DomManager domManager;
    private boolean direction;

    public Notice() {
    }

    /**
     *
     * @param id
     * @param timeSend
     * @param title
     * @param boarder
     * @param domManager
     * @param direction
     */
    public Notice(int id, Date timeSend, String title, Boarder boarder, DomManager domManager, boolean direction) {
        this.id = id;
        this.timeSend = timeSend;
        this.title = title;
        this.boarder = boarder;
        this.domManager = domManager;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Date timeSend) {
        this.timeSend = timeSend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boarder getBoarder() {
        return boarder;
    }

    public void setBoarder(Boarder boarder) {
        this.boarder = boarder;
    }

    public DomManager getDomManager() {
        return domManager;
    }

    public void setDomManager(DomManager domManager) {
        this.domManager = domManager;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }
    
    
}
