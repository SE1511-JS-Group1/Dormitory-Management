/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * DAO Implement
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      1.2                 DucHT           Update code
 */
package dao;

import dao.impl.BoarderDAO;
import dao.impl.DomManagerDAO;
import dao.impl.AccountDAO;
import java.sql.SQLException;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) throws SQLException{
        AccountDAO accountDAO = new AccountDAO();
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        BoarderDAO bdao = new BoarderDAO();
//        Account a = new Account("dinhbeo", "Thanhteo134", 3);
//        DomManager domManager = new DomManager(0, "Hoàng Trần Đức", true, new Date(2001, 5, 19), "duchthe153314@fpt.edu.vn", "0352629715", ManagerRegency.Guardian, a);
//        Boarder b = new Boarder(0, "Luu Xuan Dinh", new Date(2001, 01, 05), true, "dinhml@gmail.com", "0514287563", Jobs.Student, a);
//        accountDAO.insert(a);
//        bdao.insert(b);
        System.out.println(domManagerDAO.checkEmailDomManager("duchthe153314@fpt.edu.vn"));
    }
}
