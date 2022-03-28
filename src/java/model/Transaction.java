/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 HuyLQ           Update properties
 */
package model;

import java.sql.Date;
import java.sql.Time;

public class Transaction {

    private int transactionId;
    private String type;
    private double amount;
    private String message;
    private Date date;
    private Time time;
    private Wallet owner;

    /**
     * Initializes a newly created <code>Transaction</code> object so that it
     * represents an empty information <code>Transaction</code>.
     */
    public Transaction() {
    }

    public Transaction(int transactionId, String type, double amout, String message, Date date, Time time, Wallet owner) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amout;
        this.message = message;
        this.date = date;
        this.time = time;
        this.owner = owner;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amout) {
        this.amount = amout;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Wallet getOwner() {
        return owner;
    }

    public void setOwner(Wallet owner) {
        this.owner = owner;
    }

}
