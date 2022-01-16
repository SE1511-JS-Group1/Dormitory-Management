/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lenovo_thinkpad
 */
public class Wallet {

    private int walletId;
    private Account owner;
    private double balance;

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
