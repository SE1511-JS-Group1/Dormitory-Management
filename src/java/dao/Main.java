/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import model.*;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) {
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
