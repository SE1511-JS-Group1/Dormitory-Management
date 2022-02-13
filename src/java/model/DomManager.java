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

/**
  * The class define <code>DomManager</code> object: manager of <code>Dom</code>
  * Its properties base on DomManagerentity properties in database.
  *
  * @author DinhLX
  */
public class DomManager {

    private int managerID;
    private String name;
    private boolean gender;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private ManagerRegency regency;
    private Account account;

    /**
     * Initializes a newly created <code>DomManager</code> object so that it
     * represents an empty information <code>DomManager</code>.
     */
    public DomManager() {
    }

    /**
     * Initializes a newly created <code>DomManager</code> object with all
     * information of each <code>DomManager</code>.
     * <br> The <code>DomManager</code> information include 7 attributes: 
     * managerID, name, gender, dateOfBirth , email, phone number, regency,account.
     * 
     * <br>
     * @param managerID<code>int</code> object. manager ID provided by the school.
     * @param name<code>java.lang.String</code> object.
     * <code>manager </code>'s name
     * @param dateOfBirth <code>java.sql.Date</code> object.
     * <code>manager </code>'s date of birth
     * @param gender <code>manager </code> object. manager gender. If
     * <code>true</code>, it is Male else Female.
     * @param email <code>java.lang.String</code> object. A string express the
     * email manager.
     * @param phoneNumber <code>java.lang.String</code> object. A string of
     * numbers express the manager phone number.
     * @param regency<code>java.lang.Enum</code> object. It includes: 
     * Accountant, Management_Staff, Guardian;
     * @param account <code>Account</code> object. The account of each
     * <code>manager </code>
     */
    public DomManager(int managerID, String name, boolean gender, Date dateOfBirth, String email, String phoneNumber, ManagerRegency regency, Account account) {
        this.managerID = managerID;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.regency = regency;
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
