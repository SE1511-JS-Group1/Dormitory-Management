/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;

/**
 *
 * @author lenovo_thinkpad
 */
public class Transaction {

    private int transactionId;
    private String type;
    private double amout;
    private String message;
    private Time time;
    private Wallet owner;

    public Transaction() {
    }

    public Transaction(int transactionId, String type, double amout, String message, Time time, Wallet owner) {
        this.transactionId = transactionId;
        this.type = type;
        this.amout = amout;
        this.message = message;
        this.time = time;
        this.owner = owner;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String isType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
