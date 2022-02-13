/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Model
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-15      1.2                 DucHT           Update properties
 */

package model;

import java.util.Date;

<<<<<<< HEAD
/**
 *
 * @author ngoc duy
 */
=======
/**				
 * The class define <code>Boader</code> object: Person who join in dormitory system.			
 * Its properties base on Boader entity properties in database.				
 *				
 * @author DucHT				
 */				

>>>>>>> a3f39e5a81ae320dd2b5b520baa8c2760a385a51
public class Boarder {

    private int boarderID;
    private String boarderName;
    private Date dateOfBirth;
    private boolean gender;
    private String email;
    private String phoneNumber;
    private Jobs job;
    private Account account;

    /**
     * Initializes a newly created <code>Boarder</code> object so that it
     * represents an empty information <code>Boarder</code>.
     */
    public Boarder() {
    }

    /**
     * Initializes a newly created <code>Boarder</code> object with all
     * information of each <code>Boarder</code>.
     * <br> The <code>Boarder</code> information include 7 attributes: roll
     * number, name, date of birth, gender, email, phone number and job.
     *
     * @param rollNumber <code>int</code> object. Boarder ID provided by the
     * school.
     * @param boarderName <code>java.lang.String</code> object.
     * <code>Boarder</code>'s name
     * @param dateOfBirth <code>java.sql.Date</code> object.
     * <code>Student</code>'s date of birth
     * @param gender <code>Boolean</code> object. Student's gender. If
     * <code>true</code>, it is Male else Female.
     * @param email <code>java.lang.String</code> object. A string express the
     * boarder's email.
     * @param phoneNumber <code>java.lang.String</code> object. A string of
     * numbers express the boarder's phone number.
     * @param job <code>java.lang.Enum</code> object. It include: Student and
     * Teacher.
     * @param account <code>Account</code> object. The account of each
     * <code>Boarder</code>
     */
    public Boarder(int rollNumber, String boarderName, Date dateOfBirth, boolean gender, String email, String phoneNumber, Jobs job, Account account) {
        this.boarderID = rollNumber;
        this.boarderName = boarderName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.account = account;
    }

    /**
     * Alow user to get the Boarder's ID of this <code>Boarder</code>.<br> The
     * result contain a <code>int</code> object. Boarder's ID provided by the
     * school.
     *
     * @return a <code>java.lang.String</code> object.
     */
    public int getBoarderID() {
        return boarderID;
    }

    /**
     * Alow user to set up the Boarder's ID of this <code>Boarder</code>.
     *
     * @param boarderID <code>int</code> object. Boarder ID provided by the
     * school.
     */
    public void setBoarderID(int boarderID) {
        this.boarderID = boarderID;
    }

    /**
     * Alow user to get the Boarder's name of this <code>Boarder</code>.
     * <br> The result contain a <code>String</code> object.
     *
     * @return a <code>java.lang.String</code> object.
     */
    public String getBoarderName() {
        return boarderName;
    }

    /**
     * Alow user to set up the Boarder's name of this <code>Boarder</code>.
     *
     *
     * @param boarderName <code>java.lang.String</code> object.
     */
    public void setBoarderName(String boarderName) {
        this.boarderName = boarderName;
    }
//

    /**
     * Alow user to get Date of birth .....
     *
     * @return <code>java.sql.Date</code> object.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
