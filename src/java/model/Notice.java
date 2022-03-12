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

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * The class define <code>Notice</code> object: <code>Notice</code> of dormitory
 * system. Its properties base on Account entity properties in database.
 *
 * @author AnhNNV
 */
public class Notice {

    private int id;
    private Time timeSend;
    private Date dateSend;
    private String title;
    private Boarder boarder;
    private DomManager domManager;
    private boolean direction;

    /**
     * Initializes a newly created <code>Notice</code> object so that it
     * represents an empty information <code>Notice</code>.
     */
    public Notice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Time timeSend) {
        this.timeSend = timeSend;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
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

    public Notice(int id, Time timeSend, Date dateSend, String title, Boarder boarder, DomManager domManager, boolean direction) {
        this.id = id;
        this.timeSend = timeSend;
        this.dateSend = dateSend;
        this.title = title;
        this.boarder = boarder;
        this.domManager = domManager;
        this.direction = direction;
    }

}
