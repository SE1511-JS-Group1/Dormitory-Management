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

/**
 * The class define <code>Account</code> object: Account of Person who join in
 * dormitory system. Its properties base on Account entity properties in
 * database.
 *
 * @author DucHT
 */
public class Account {

    private String userName;//
    private String passWord;//
    private int role;//

    /**
     * Initializes a newly created <code>Account</code> object so that it
     * represents an empty information <code>Account</code>.
     */
    public Account() {
    }

    /**
     * Initializes a newly created <code>Account</code> object with all
     * information of each <code>Account</code>.
     * <br> The <code>Account</code> information include 3 attributes: UserName,
     * PassWorld, Role.
     * <br>
     *
     * @param UserName <code>java.lang.String</code> object
     * <code>userName</code>in Account of Boarder is used to identify a boarder
     * in the dormitory system.
     * @param PassWorld<code>java.lang.String</code> password of.
     * <code>Account</code>
     * @param Role<code>int</code> when creating and logging in
     * <code>Account</code> will be authorized to use the respective rights and
     * functions.
     */
    public Account(String userName, String passWord, int role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    /**
     * Allows the user to get the userName of the Account of this
     * <code> Boarder</code>.
     * <br>
     * the result contains a <code> java.lang.String </code> object.
     * <code>userName</code> of Account generated by Boarder upon registration
     * <br>
     *
     * @return a <code> java.lang.String </code> object.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Allows users to set the Boarder Username of this <code> Account </code>
     * object
     * <br>
     *
     * @param userName <code> java.lang.String </code> object. of the generated
     * Account created by Boarder upon registration
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Allows the user to get the passWord of the Account of this
     * <code> Boarder</code>.
     * <br>
     * the result contains a <code> java.lang.String </code> object.
     * <code>passWord</code> of Account generated by Boarder upon registration
     * <br>
     *
     * @return a <code> java.lang.String </code> object.
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Allows users to set the Boarder passWord of this <code> Account </code>
     * object
     * <br>
     *
     * @param passWord <code> java.lang.String </code> object. of the generated
     * Account created by Boarder upon registration
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Allow user to get the role of this <code>Account</code> object.
     * <br>
     * result contains a <code>int</code> object. indicates what function
     * <code>Account</code> is authorized with: admin, staff or boarder
     *
     * @return a <code>int</code> object.
     */
    public int getRole() {
        return role;
    }

    /**
     * Allow user to set up the Role of this <code>Account</code> object.
     * <br>
     *
     * @param role<code>int</code> object. Account role created upon
     * registration
     */
    public void setRole(int role) {
        this.role = role;
    }

}
