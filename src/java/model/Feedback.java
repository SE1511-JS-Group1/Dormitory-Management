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
public class Feedback {

    private int feedbackId;
    private Date timeSend;
    private String title;
    private Boarder owner;

    public Feedback() {
    }

    public Feedback(int feedbackId, Date timeSend, String title, Boarder owner) {
        this.feedbackId = feedbackId;
        this.timeSend = timeSend;
        this.title = title;
        this.owner = owner;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public Boarder getOwner() {
        return owner;
    }

    public void setOwner(Boarder owner) {
        this.owner = owner;
    }

}
