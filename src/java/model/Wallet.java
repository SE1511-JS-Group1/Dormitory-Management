/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DuyTN          Update properties
 */
package model;

public class Wallet {

    private int walletId;
    private Account owner;
    private double balance;

    /**
     * Initializes a newly created <code>Wallet</code> object so that it
     * represents an empty information <code>Wallet</code>.
     */
    public Wallet() {
    }

    public Wallet(int walletId, Account owner, double balance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = balance;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
