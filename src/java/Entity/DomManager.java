/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;

/**
 *
 * @author lenovo_thinkpad
 */
class DomManager {

    private int managerID;
    private String name;
    private boolean gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private ManagerRegency regency;
    private Account account;

    public DomManager() {
    }

    public DomManager(int managerID, String name, boolean gender, Date dateOfBirth, String phoneNumber, ManagerRegency regency, Dom dom, Account account) {
        this.managerID = managerID;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.regency = regency;
        this.account = account;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ManagerRegency getRegency() {
        return regency;
    }

    public void setRegency(ManagerRegency regency) {
        this.regency = regency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
