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

import java.sql.Date;
import java.sql.Time;

/**
 * The class define <code>Feedback</code> object: response of
 * <code>Boarder</code> dormitory system. Its properties base on Account entity
 * properties in database.
 *
 * @author DinhLX
 */
public class Feedback {

    private int feedbackId;
    private Date dateSend;
    private Time timeSend;
    private String title;
    private Boarder owner;

    /**
     * Initializes a newly created <code>Feedback</code> object so that it
     * represents an empty information <code>Feedback</code>.
     */
    public Feedback() {
    }

    /**
     * Initializes a newly created <code>Feedback</code> object with all
     * information of each <code>Feedback</code>.
     * <br> The <code>Feedback</code> information includes 3 attributes:
 feedbackId, dateSend, title, owner.
 <br>
     *
     * @param feedbackId<code>int</code> object <code>feedbackId</code> is
     * automatically increased by the dormitory management system
     * @param dateSend
     * @param timeSend<code>java.sql.Date</code> object. submission date of
     * <code>Feedback</code>'s
     * @param title<code>java.sql.String</code> content <code>Feedback</code>
     * @param owner<code>boarder</code> info <code>Boarder</code>sent
     * <code>Feedback</code>
     */
    public Feedback(int feedbackId, Date dateSend,Time timeSend, String title, Boarder owner) {
        this.feedbackId = feedbackId;
        this.dateSend = dateSend;
        this.timeSend =timeSend;
        this.title = title;
        this.owner = owner;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public Boarder getOwner() {
        return owner;
    }

    public void setOwner(Boarder owner) {
        this.owner = owner;
    }

}
